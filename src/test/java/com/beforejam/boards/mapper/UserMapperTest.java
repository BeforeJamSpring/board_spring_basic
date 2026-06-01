package com.beforejam.boards.mapper;


import com.beforejam.boards.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

@MybatisTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void saveUserTest() throws Exception {
        // given
        User user = User.builder().username("TESTUSER")
                .password("1234")
                .email("TEST@test.com")
                .name("테스트").
                build();
        // when
        userMapper.save(user);
        // then
        User found = userMapper.findByUsername("테스트");
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("테스트");
    }
}