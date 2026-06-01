package com.beforejam.boards.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
//파라미터 순서 실수를 유발하는 전체 생성자 직접 호출 방지
@AllArgsConstructor(access = PRIVATE)
@Builder
public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String name;
}
