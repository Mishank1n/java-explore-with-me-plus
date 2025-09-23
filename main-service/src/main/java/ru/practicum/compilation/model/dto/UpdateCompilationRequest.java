package ru.practicum.compilation.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Set;

@Getter
@Setter
public class UpdateCompilationRequest {
    private Set<Long> events;
    private Boolean pinned;
    @Nullable
    @Size(min = 1, max = 50)
    private String title;
}
