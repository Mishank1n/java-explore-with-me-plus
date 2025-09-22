package ru.practicum.event.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.category.model.dto.CategoryDto;
import ru.practicum.event.model.EventState;
import ru.practicum.event.model.Location;
import ru.practicum.user.model.dto.UserShortDto;

@Getter
@Setter
public class EventFullDto {
    private long id;
    @NotBlank
    private String annotation;
    @NotNull
    private CategoryDto category;
    private int confirmedRequests;
    @NotBlank
    private String createdOn;
    @NotBlank
    private String description;
    @NotBlank
    private String eventDate;
    @NotNull
    private UserShortDto initiator;
    @NotNull
    private Location location;
    @NotNull
    private boolean paid;
    private int participantLimit = 0;
    @NotBlank
    private String publishedOn;
    private boolean requestModeration = false;
    private EventState state = EventState.PENDING;
    @NotBlank
    private String title;
    private long views;
}