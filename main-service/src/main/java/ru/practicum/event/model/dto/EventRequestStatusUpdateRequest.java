package ru.practicum.event.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class EventRequestStatusUpdateRequest {
    private List<Integer> requestIds;
    private UpdateRequestState status;
}