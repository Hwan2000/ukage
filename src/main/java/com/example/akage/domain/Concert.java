package com.example.akage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime ticketTime;

    private String ticketSite; // 나중에 추가해도 됨
    private String ticketLink;
    private String poster;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    @Builder.Default //false를 기본값으로 설정
    @Column(nullable = false)
    private boolean approved = false;
}

