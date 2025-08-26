package com.example.akage.repository;

import com.example.akage.domain.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConcertRepository extends JpaRepository<Concert, UUID>{
    List<Concert> findByApprovedTrue();
}
