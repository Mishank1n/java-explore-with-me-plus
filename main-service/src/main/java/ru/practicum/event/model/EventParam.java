package ru.practicum.event.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class EventParam {
    private Long userId;
    private Long eventId;

    private String text;
    private List<Long> categories;
    private Boolean paid;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;

    private Boolean onlyAvailable;
    private String sort;

    private List<Long> users;
    private List<String> states;

    private Integer from;
    private Integer size;

    private String requestUri;
    private HttpServletRequest request;
}
