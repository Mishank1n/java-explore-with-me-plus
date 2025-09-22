package ru.practicum.event.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import ru.practicum.event.model.Location;

@Getter
@Setter
public class UpdateEventUserRequest {
    @Nullable
    @Size(min = 20, max = 2000)
    private String annotation;
    private Long category;
    @Nullable
    @Size(min = 20, max = 7000)
    private String description;
    private String eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String stateAction;
    @Nullable
    @Size(min = 3, max = 120)
    private String title;
}