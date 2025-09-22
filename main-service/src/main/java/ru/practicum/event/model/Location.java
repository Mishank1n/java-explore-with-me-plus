package ru.practicum.event.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Location {
    private float lat; // широта
    private float lon; // долгота
}
