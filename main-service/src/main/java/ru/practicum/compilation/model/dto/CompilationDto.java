package ru.practicum.compilation.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.event.model.dto.EventShortDto;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CompilationDto {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    private boolean pinned;
    @NotNull
    private Set<EventShortDto> events = new HashSet<>();
}
