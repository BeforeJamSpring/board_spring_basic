package com.beforejam.boards.controller;

import com.beforejam.boards.domain.User;
import com.beforejam.boards.service.UserService;
import com.beforejam.boards.util.TestUserUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//웹 레이어만 쏙 잘라서 테스트하는 가벼운 환경 설정
@WebMvcTest(UserController.class)
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
        User user = TestUserUtil.createTestUser();
        // when
        //doNothing(): 반환 타입이 void인 메서드의 가짜 행위를 정의할 때 사용
        doNothing().when(userService).signUp(any(User.class));

        // then
        mockMvc.perform(post("/users/signup").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(user))).andExpect(status().isCreated());
    }

    @Test
    void signupDuplicateUserTest() throws Exception {
        // given
        User user = TestUserUtil.createTestUser();

        // when
        doThrow(new IllegalArgumentException("이미 존재하는 아이디입니다."))
                .when(userService)
                //기존 메모리 주소가 같은 user가 들어 왔을 때 예외처리를 시도
                .signUp(any(User.class));
        // then
        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(user))) // @RequestBody는 새로운 User 객체를 만들어 냄
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("이미 존재하는 아이디입니다."));
    }
}