package ru.practicum.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comment.model.Comment;
import ru.practicum.comment.model.dto.CommentRequest;
import ru.practicum.comment.model.dto.CommentResponse;
import ru.practicum.comment.model.mapper.CommentMapper;
import ru.practicum.comment.repository.CommentRepository;
import ru.practicum.event.model.Event;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.exception.ConflictException;
import ru.practicum.exception.NotFoundException;
import ru.practicum.user.model.User;
import ru.practicum.user.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImp implements CommentService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CommentRepository commentRepository;


    @Override
    @Transactional
    public CommentResponse createComment(Long userId, Long eventId, CommentRequest commentRequest) {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        Event event = eventRepository.findById(eventId).get();
        if (event == null) {
            throw new NotFoundException("Событие не найдено");
        }

        Comment comment = new Comment();
        comment.setText(commentRequest.getText());
        comment.setCreated(LocalDateTime.now());
        comment.setAuthor(user);
        comment.setEvent(event);

        Comment newComment = commentRepository.save(comment);
        return CommentMapper.toCommentResponse(newComment);
    }

    @Override
    @Transactional
    public CommentResponse updateComment(Long userId, Long commentId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentId).get();
        if (comment == null) {
            throw new NotFoundException("Комментарий не найден");
        }

        User user = userRepository.findById(userId).get();

        if (user == null) {
            throw new NotFoundException("Пользователь не найден");
        }

        if (comment.getAuthor().getId() != userId) {
            throw new ConflictException("Только автор может редактировать комментарий");
        }
        comment.setText(commentRequest.getText());
        Comment updatedComment = commentRepository.save(comment);
        return CommentMapper.toCommentResponse(updatedComment);
    }
}
