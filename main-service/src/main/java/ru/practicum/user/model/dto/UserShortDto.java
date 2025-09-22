package ru.practicum.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShortDto {
        @NotNull
        private long id;
        @NotBlank
        private String name;
}
