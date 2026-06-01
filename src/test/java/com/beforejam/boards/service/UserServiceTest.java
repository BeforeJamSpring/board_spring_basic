package com.beforejam.boards.service;

import com.beforejam.boards.domain.User;
import com.beforejam.boards.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void signUpSuccessTest() throws Exception {
        // given
        User user = User.builder().username("TESTId").build();
        given(userMapper.findByUsername("TESTId")).willReturn(null);

        // when
        userService.signUp(user);

        // then
        verify(userMapper).save(any(User.class));
    }

    @Test
    void signUpDuplicateUsernameTest() throws Exception {
        // given
        User user = User.builder().username("DuplicateId").build();
        User duplicateUser = User.builder().username("DuplicateId").build();

        given(userMapper.findByUsername("DuplicateId")).willReturn(duplicateUser);

        // when
        // then
        Assertions.assertThatThrownBy(() -> userService.signUp(user))
        .isInstanceOf(IllegalArgumentException.class);
    }
}