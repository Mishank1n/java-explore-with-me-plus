package ru.practicum.category.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Field: name. Error: must not be blank. Value: null")
    String name;
}
