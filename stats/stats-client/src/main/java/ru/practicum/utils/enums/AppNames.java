package ru.practicum.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppNames {
    MAIN_SERVICE("ewm-main-service");

    private final String name;
}
