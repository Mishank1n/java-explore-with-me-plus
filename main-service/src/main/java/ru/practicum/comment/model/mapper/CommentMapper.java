package ru.practicum.comment.model.mapper;

import ru.practicum.comment.model.Comment;
import ru.practicum.comment.model.dto.CommentRequest;
import ru.practicum.comment.model.dto.CommentResponse;
import ru.practicum.event.model.Event;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;

public class CommentMapper {
    public static Comment toComment(CommentRequest commentRequest, User author, Event event) {
        Comment comment = new Comment();
        comment.setText(commentRequest.getText());
        comment.setAuthor(author);
        comment.setCreated(LocalDateTime.now());
        comment.setEvent(event);
        return comment;
    }

    public static CommentResponse toCommentResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setText(comment.getText());
        commentResponse.setCreated(comment.getCreated());
        commentResponse.setAuthor(comment.getAuthor().getId());
        commentResponse.setEvent(comment.getEvent().getId());
        return commentResponse;
    }
}
