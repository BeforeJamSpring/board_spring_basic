package com.beforejam.boards.mapper;

import com.beforejam.boards.domain.User;
import com.beforejam.boards.util.TestUserUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@Sql("/schema.sql")
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void saveUserTest() throws Exception {
        // given
        User user = TestUserUtil.createTestUser();

        // when
        userMapper.save(user);
        User found = userMapper.findByUsername("TESTER");

        // then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("테스터");
    }
}