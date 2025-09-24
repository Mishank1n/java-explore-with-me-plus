package ru.practicum.request.service;

import ru.practicum.request.model.dto.RequestDto;

import java.util.List;

public interface RequestService {

    List<RequestDto> getAll(Long userId);

    RequestDto create(Long userId, Long eventId);

    RequestDto cancelRequest(Long userId, Long requestId);
}
