package com.example.akage.resolver;


import com.example.akage.dto.ConcertInput;
import com.example.akage.dto.ConcertResponse;
import com.example.akage.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class ConcertResolver {

    private final ConcertService concertService;

    @Autowired
    public ConcertResolver(ConcertService concertService) {
        this.concertService = concertService;
    }

    @QueryMapping
    public List<ConcertResponse> getConcerts() {
        List<ConcertResponse> list = concertService.findApprovedConcerts();
        return (list != null) ? list : Collections.emptyList();
    }

    @QueryMapping
    public ConcertResponse getConcertById(@Argument UUID id) {   // ← id에도 @Argument
        return concertService.findById(id);
    }

    @MutationMapping
    public ConcertResponse createConcert(@Argument("input") ConcertInput input) { // ← 여기!
        return concertService.createConcert(input);
    }

    @MutationMapping
    public ConcertResponse approveConcert(@Argument UUID id) {   // ← id에도 @Argument
        return concertService.approveConcert(id);
    }
}
