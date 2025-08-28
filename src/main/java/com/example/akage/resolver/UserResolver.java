package com.example.akage.resolver;

import com.example.akage.dto.UserResponse;
import com.example.akage.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserResolver {

    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    // 유저 조회
    @QueryMapping
    public UserResponse getUser(@Argument UUID id) {
        return userService.findUserResponseById(id);
    }

    // 찜 추가
    @MutationMapping
    public UserResponse addZzim(@Argument UUID userId,
                                @Argument UUID concertId) {
        userService.addZzimConcert(userId, concertId);
        return userService.findUserResponseById(userId);
    }

    // 찜 제거
    @MutationMapping
    public UserResponse removeZzim(@Argument UUID userId,
                                   @Argument UUID concertId) {
        userService.removeZzimConcert(userId, concertId);
        return userService.findUserResponseById(userId);
    }
}


