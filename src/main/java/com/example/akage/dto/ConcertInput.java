package com.example.akage.dto;

import com.example.akage.domain.Genre;
import com.example.akage.domain.Locate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertInput {
    private String title;
    private String place;
    private Locate locate;
    private String address;
    private LocalDateTime startTime;
    private LocalDateTime ticketTime;
    private String ticketSite;
    private String ticketLink;
    private String poster;
    private Genre genre;
    private List<String> artist;
    private Map<String, Integer> price;
}
