package ru.practicum.event.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestDto {
    private int id;
    private String created;
    private int event;
    private int requester;
    private String status;
}