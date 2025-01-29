package dev.api.converter;

import dev.api.entity.Comment;
import dev.api.web.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CommentDTO convertCommentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        return commentDTO;
    }

    public Comment convertCommentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        return comment;
    }
}