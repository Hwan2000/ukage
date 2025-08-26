package com.example.akage.dto;

import com.example.akage.domain.Genre;
import com.example.akage.domain.Locate;
import com.example.akage.domain.PriceEntry;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertResponse {
    private UUID id;
    private String title;
    private String place;
    private Locate locate;
    private String address;
    private OffsetDateTime startTime;
    private OffsetDateTime ticketTime;
    private String ticketSite;
    private String ticketLink;
    private String poster;
    private Genre genre;
    private List<String> artist;
    private List<PriceEntry> price;
    private boolean approved;
}
