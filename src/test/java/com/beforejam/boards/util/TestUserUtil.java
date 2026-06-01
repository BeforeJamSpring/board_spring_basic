package com.beforejam.boards.util;

import com.beforejam.boards.domain.User;

public class TestUserUtil {
    public static User createTestUser() {
        return User.builder()
                .username("TESTER")
                .password("1234")
                .email("test@test.com")
                .name("테스터")
                .build();
    }
}
