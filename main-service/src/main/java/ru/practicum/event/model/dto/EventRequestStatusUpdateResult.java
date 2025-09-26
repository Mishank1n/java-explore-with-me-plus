package ru.practicum.event.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.request.model.dto.RequestDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EventRequestStatusUpdateResult {
    private List<RequestDto> confirmedRequests = new ArrayList<>();
    private List<RequestDto> rejectedRequests = new ArrayList<>();
}