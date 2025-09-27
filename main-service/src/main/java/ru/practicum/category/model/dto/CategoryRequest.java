package ru.practicum.category.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "Field: name. Error: must not be blank. Value: null")
    @Length(max = 50, min = 1)
    String name;
}
