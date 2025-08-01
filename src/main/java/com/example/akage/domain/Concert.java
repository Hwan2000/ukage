package com.example.akage.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
public class Concert {
    @Id
    private UUID id;

    private String title;
    private String place;

    @Enumerated(EnumType.STRING)
    private Locate locate;

    private String address;

    private LocalDateTime startTime;
    private LocalDateTime ticketTime;

    private String ticketSite;
    private String ticketLink;
    private String poster;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ElementCollection
    @CollectionTable(name = "concert_artist", joinColumns = @JoinColumn(name = "concert_id"))
    @Column(name = "artist")
    private List<String> artist;

    @ElementCollection
    @CollectionTable(name = "concert_price", joinColumns = @JoinColumn(name = "concert_id"))
    @MapKeyColumn(name = "seat_type")
    @Column(name = "price")
    private Map<String, Integer> price;

    private boolean approved = false;
}

