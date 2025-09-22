package ru.practicum.event.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.model.EventParam;
import ru.practicum.event.model.dto.EventFullDto;
import ru.practicum.event.model.dto.EventShortDto;
import ru.practicum.event.service.EventService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@Validated
@RequiredArgsConstructor
@Slf4j
public class EventControllerPublic {
    private final EventService eventService;
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text", required = false) String text,
                                         @RequestParam(name = "categories", required = false) List<Long> categories,
                                         @RequestParam(name = "paid", required = false) Boolean paid,
                                         @RequestParam(name = "rangeStart", required = false) @DateTimeFormat(pattern = DATE_TIME_PATTERN) LocalDateTime rangeStart,
                                         @RequestParam(name = "rangeEnd", required = false) @DateTimeFormat(pattern = DATE_TIME_PATTERN) LocalDateTime rangeEnd,
                                         @RequestParam(name = "onlyAvailable", required = false) Boolean onlyAvailable,
                                         @RequestParam(name = "sort", required = false) String sort,
                                         @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero int from,
                                         @RequestParam(name = "size", defaultValue = "10") @Positive int size,
                                         HttpServletRequest request)  {
        EventParam p = EventParam.builder()
                .text(text)
                .categories(categories)
                .paid(paid)
                .rangeStart(rangeStart)
                .rangeEnd(rangeEnd)
                .onlyAvailable(onlyAvailable)
                .sort(sort)
                .from(from)
                .size(size)
                .requestUri(request.getRequestURI())
                .build();
        List<EventShortDto> eventDtos = eventService.getEvents(p);

        log.info("Выполнен запрос получения всех событий");
        return eventDtos;
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEvent(@PathVariable(name = "eventId") int eventId,
                                                 HttpServletRequest request) {
        log.info("Выполнен запрос получения события с id={}", eventId);
        EventFullDto eventDto = eventService.getEvent(eventId, request);
        return eventDto;
    }
}
