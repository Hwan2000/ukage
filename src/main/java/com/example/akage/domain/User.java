package com.example.akage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "user_zzim", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "concert_id")
    @Builder.Default
    private List<UUID> zzimConcertIds = new ArrayList<>();
}
