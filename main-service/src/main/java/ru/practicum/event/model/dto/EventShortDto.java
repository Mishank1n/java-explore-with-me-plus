package ru.practicum.event.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.category.model.dto.CategoryDto;
import ru.practicum.user.model.dto.UserDto;



@Getter
@Setter
public class EventShortDto {
    private int id;
    @NotBlank
    private String annotation;
    @NotNull
    private CategoryDto category;
    private int confirmedRequests;
    @NotBlank
    private String eventDate;
    @NotNull
    private UserDto initiator;
    @NotNull
    private boolean paid;
    @NotBlank
    private String title;
    private long views;
}