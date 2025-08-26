package com.example.akage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // 생성자 생성
@Builder // 가독성 올려줌
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // UUID 직접생성
    private UUID id;

    @Column(nullable = false) // 데이터가 없거나 누락된 상태로 저장되는거 방지
    private String title;

    @Column(nullable = false)
    private String place;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Locate locate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private OffsetDateTime startTime;

    @Column(nullable = false)
    private OffsetDateTime ticketTime;

    private String ticketSite; // 나중에 추가해도 됨
    private String ticketLink;
    private String poster;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @ElementCollection
    @CollectionTable(name = "concert_artist", joinColumns = @JoinColumn(name = "concert_id"))
    @Column(name = "artist")
    @Builder.Default
    private List<String> artist = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "concert_prices", joinColumns = @JoinColumn(name = "concert_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "seatType", column = @Column(name = "seat_type")),
            @AttributeOverride(name = "price",    column = @Column(name = "price"))
    })
    @Builder.Default
    private List<PriceEntry> price = new ArrayList<>();

    @Builder.Default //false를 기본값으로 설정
    @Column(nullable = false)
    private boolean approved = false;
}

