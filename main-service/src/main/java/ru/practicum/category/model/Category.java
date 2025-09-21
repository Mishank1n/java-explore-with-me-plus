package ru.practicum.category.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories", schema = "public")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;
}
