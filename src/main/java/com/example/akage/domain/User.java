package com.example.akage.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "user_zzim", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "concert_id")
    private List<UUID> zzimConcertIds = new ArrayList<>();
}
