package ru.practicum.comment.service;

import ru.practicum.comment.model.dto.CommentRequest;
import ru.practicum.comment.model.dto.CommentResponse;

public interface CommentService {
    CommentResponse createComment(Long userId, Long eventId, CommentRequest commentRequest);

    CommentResponse updateComment(Long userId, Long eventId, CommentRequest commentRequest);
}
