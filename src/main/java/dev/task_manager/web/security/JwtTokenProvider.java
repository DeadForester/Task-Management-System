package dev.task_manager.web.security;

import dev.task_manager.entity.exception.AccessDeniedException;
import dev.task_manager.entity.user.Role;
import dev.task_manager.entity.user.User;
import dev.task_manager.services.UserService;
import dev.task_manager.services.props.JwtProperties;
import dev.task_manager.web.dto.auth.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(Long userId, String username, Set<Role> roles) {
        Date now = new Date();
        Date validate = new Date(now.getTime() + jwtProperties.getAccess());

        return Jwts.builder()
                .subject(username)
                .claim("id", userId)
                .claim("roles", resolveRoles(roles))
                .issuedAt(now)
                .expiration(validate)
                .signWith(key)
                .compact();
    }

    private List<String> resolveRoles(Set<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public String createRefreshToken(Long userId, String username) {
        Date now = new Date();
        Date validate = new Date(now.getTime() + jwtProperties.getRefresh());
        return Jwts.builder()
                .subject(username)
                .claim("id", userId)
                .issuedAt(now)
                .expiration(validate)
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserTokens(String refreshToken) {
        JwtResponse jwtResponse = new JwtResponse();
        if (!validateToken(refreshToken)) {
            throw new AccessDeniedException();
        }
        Long userId = Long.valueOf(getId(refreshToken));
        User user = userService.getById(userId);
        jwtResponse.setUsername(user.getUserName());
        jwtResponse.setAccessToken(createAccessToken(userId, user.getUserName(), user.getRoles()));
        jwtResponse.setRefreshToken(createRefreshToken(userId, user.getUserName()));
        return jwtResponse;

    }

    //Старое решение (временная затычка - не баг, а фига) надо будет поправить!!!
    // Или не временная...
    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts
                .parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());

    }


    public Authentication getAuthentication(String token){
        String username = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts
                .parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }



    private String getId(String token) {
        return Jwts
                .parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
    }

}
