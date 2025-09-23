package ru.practicum.compilation.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class NewCompilationDto {
    private Set<Long> events;
    private boolean pinned;
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;
}
