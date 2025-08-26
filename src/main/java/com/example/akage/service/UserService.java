package com.example.akage.service;

import com.example.akage.domain.User;
import com.example.akage.dto.UserResponse;
import com.example.akage.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // User 엔티티 → UserResponse DTO 변환
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .zzimConcertIds(user.getZzimConcertIds())
                .build();
    }

    // 유저 조회 (엔티티 반환)
    public User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }

    // 유저 조회 (DTO 반환용)
    public UserResponse findUserResponseById(UUID userId) {
        User user = findUserById(userId);
        return toResponse(user);
    }

    // 찜 추가 (비즈니스 로직)
    @Transactional
    public User addZzimConcert(UUID userId, UUID concertId) {
        User user = findUserById(userId);
        if (!user.getZzimConcertIds().contains(concertId)) {
            user.getZzimConcertIds().add(concertId);
        }
        return userRepository.save(user);
    }

    // 찜 제거 (비즈니스 로직)
    @Transactional
    public User removeZzimConcert(UUID userId, UUID concertId) {
        User user = findUserById(userId);
        user.getZzimConcertIds().remove(concertId);
        return userRepository.save(user);
    }
}
