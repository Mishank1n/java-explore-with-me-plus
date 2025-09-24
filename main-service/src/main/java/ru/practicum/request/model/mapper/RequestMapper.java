package ru.practicum.request.model.mapper;

import ru.practicum.request.model.Request;
import ru.practicum.request.model.dto.RequestDto;

public class RequestMapper {
    public static RequestDto toRequestDto(Request request){
        RequestDto dto = new RequestDto();
        dto.setCreated(request.getCreated());
        dto.setEvent(request.getEvent().getId());
        dto.setId(request.getId());
        dto.setRequester(request.getRequester().getId());
        dto.setStatus(request.getStatus());
        return dto;
    }
}
