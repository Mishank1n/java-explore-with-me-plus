package ru.practicum.event.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.category.model.Category;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "events", schema = "public")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "annotation")
    private String annotation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "confirmed_requests")
    private int confirmedRequests;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "description")
    private String description;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator")
    private User initiator;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "location_lat")),
            @AttributeOverride(name = "lon", column = @Column(name = "location_lon"))})
    @Column(name = "location")
    private Location location;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "participant_limit")
    private int participantLimit;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    private boolean requestModeration;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EventState state = EventState.PENDING;

    @Column(name = "title")
    private String title;

    @Column(name = "views")
    private int views;

}
