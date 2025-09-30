package ru.practicum.comment.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.model.dto.CommentRequest;
import ru.practicum.comment.model.dto.CommentResponse;
import ru.practicum.comment.service.CommentService;

@RestController
@RequestMapping("/event/{eventId}/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse createComment(@PathVariable("eventId") Long eventId,
                                         @RequestHeader("X-User-Id") Long userId,
                                         @Valid @RequestBody CommentRequest commentRequest) {
        return commentService.createComment(userId, eventId, commentRequest);
    }

    @PatchMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable("eventId") Long eventId,
                                         @PathVariable("commentId") Long commentId,
                                         @RequestHeader("X-User-Id") Long userId,
                                         @Valid @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(userId, commentId, commentRequest);
    }

}
