package dev.api.converter;

import dev.api.entity.Comment;
import dev.api.web.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    private final ModelMapper modelMapper;

    private CommentDTO convertCommentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        return commentDTO;
    }

    private Comment convertCommentDTOToComment(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return comment;
    }

}
