package com.example.akage.service;

import com.example.akage.domain.Concert;
import com.example.akage.dto.ConcertInput;
import com.example.akage.dto.ConcertResponse;
import com.example.akage.repository.ConcertRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ConcertService {
    private final ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository){
        this.concertRepository = concertRepository;
    }


    private ConcertResponse toResponse(Concert concert){
        return ConcertResponse.builder()
                .id(concert.getId())
                .title(concert.getTitle())
                .place(concert.getPlace())
                .locate(concert.getLocate())
                .address(concert.getAddress())
                .startTime(concert.getStartTime())
                .ticketTime(concert.getTicketTime())
                .ticketSite(concert.getTicketSite())
                .ticketLink(concert.getTicketLink())
                .poster(concert.getPoster())
                .genre(concert.getGenre())
                .artist(concert.getArtist())
                .price(concert.getPrice())
                .approved(concert.isApproved())
                .build();
    } // 엔티티 -> dto 변환

    private Concert toEntity(ConcertInput input){
        return Concert.builder()
                .title(input.getTitle())
                .place(input.getPlace())
                .locate(input.getLocate())
                .address(input.getAddress())
                .startTime(input.getStartTime())
                .ticketTime(input.getTicketTime())
                .ticketSite(input.getTicketSite())
                .ticketLink(input.getTicketLink())
                .poster(input.getPoster())
                .genre(input.getGenre())
                .artist(input.getArtist())
                .price(input.getPrice())
                .approved(false) // 기본 미승인 상태
                .build();
    } // dto -> 엔티티 변환

    public List<ConcertResponse> findApprovedConcerts() {
        return concertRepository.findByApprovedTrue()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ConcertResponse findById(UUID id){
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Concert not found with  id: " + id));
        return toResponse(concert);
    }

    @Transactional
    public ConcertResponse createConcert(ConcertInput input){
        Concert concert = toEntity(input);
        Concert saved = concertRepository.save(concert);
        return toResponse(saved);
    }

    @Transactional
    public ConcertResponse approveConcert(UUID id) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Concert not found with id: " + id));
        concert.setApproved(true);
        Concert saved = concertRepository.save(concert);
        return toResponse(saved);
    }
}
