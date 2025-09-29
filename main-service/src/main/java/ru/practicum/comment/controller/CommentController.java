package ru.practicum.comment.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event/{eventId}/comment")
@AllArgsConstructor
public class CommentController {

}
