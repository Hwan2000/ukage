package com.example.akage.resolver;

import com.example.akage.dto.UserResponse;
import com.example.akage.service.UserService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserResolver {

    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    // GraphQL 쿼리: 유저 정보 조회 (DTO 반환)
    @QueryMapping
    public UserResponse getUser(UUID id) {
        return userService.findUserResponseById(id);
    }

    // GraphQL 뮤테이션: 찜 추가
    @MutationMapping
    public UserResponse addZzim(UUID userId, UUID concertId) {
        userService.addZzimConcert(userId, concertId);
        return userService.findUserResponseById(userId);
    }

    // GraphQL 뮤테이션: 찜 제거
    @MutationMapping
    public UserResponse removeZzim(UUID userId, UUID concertId) {
        userService.removeZzimConcert(userId, concertId);
        return userService.findUserResponseById(userId);
    }
}

