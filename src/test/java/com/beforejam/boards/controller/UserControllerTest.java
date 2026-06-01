package com.beforejam.boards.controller;

import com.beforejam.boards.domain.User;
import com.beforejam.boards.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//웹 레이어만 쏙 잘라서 테스트하는 가벼운 환경 설정
@WebMvcTest(UserControllerTest.class)
class UserControllerTest {

    // 가짜로 HTTP 요청을 던져주는 테스트 전용 가상 브라우저 로봇
    @Autowired
    private MockMvc mockMvc;

    // 자바 객체 <-> JSON 문자열 간의 상호 변환기
    @Autowired
    private ObjectMapper om;

    // 가짜 빈(Mock)을 스프링 컨테이너에 갈아 끼움
    @MockitoBean
    private UserService userService;

    @Test
    void signupSuccessTest() throws Exception {
        // given
        User user = User.builder().username("controllerUSer")
                .password("1234")
                .email("Test@Test.com")
                .name("Test")
                .build();

        // when
        //doNothing(): 반환 타입이 void인 메서드의 가짜 행위를 정의할 때 사용
        doNothing().when(userService).signUp(any(User.class));

        // then
        mockMvc.perform(post("/users/signup").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(user))).andExpect(status().isCreated());
    }
}