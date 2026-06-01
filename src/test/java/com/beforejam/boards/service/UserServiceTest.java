package com.beforejam.boards.service;

import com.beforejam.boards.domain.User;
import com.beforejam.boards.mapper.UserMapper;
import com.beforejam.boards.util.TestUserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        assertThatThrownBy(() -> userService.signUp(user))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getUserSuccessTest() throws Exception {
        // given
        User user = TestUserUtil.createTestUser();
        given(userMapper.findByUsername("TESTER")).willReturn(user);

        // when
        User found = userService.getUser("TESTER");
        // then
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("TESTER");
        assertThat(found.getName()).isEqualTo("테스터");
    }
}