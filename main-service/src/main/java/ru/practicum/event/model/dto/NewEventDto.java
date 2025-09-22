package ru.practicum.event.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.event.model.Location;

@Getter
@Setter
public class NewEventDto {
    @NotNull
    @Size(min = 20, max = 2000)
    private String annotation;
    @NotNull
    private int category;
    @NotNull
    @Size(min = 20, max = 7000)
    private String description;
    @NotBlank
    private String eventDate;
    @NotNull
    private Location location;
    @NotNull
    private boolean paid = false;
    private int participantLimit = 0;
    private boolean requestModeration = true;
    @Size(min = 3, max = 120)
    private String title;
}
