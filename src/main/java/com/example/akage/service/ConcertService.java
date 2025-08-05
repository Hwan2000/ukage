package com.example.akage.service;

import com.example.akage.repository.ConcertRepository;

public class ConcertService {
    private final ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository){
        this.concertRepository = concertRepository;
    }
}
