package com.beforejam.boards.service;

import com.beforejam.boards.domain.User;
import com.beforejam.boards.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void signUp(User user){

    }
}
