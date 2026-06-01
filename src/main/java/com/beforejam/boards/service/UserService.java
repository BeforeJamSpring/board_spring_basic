package com.beforejam.boards.service;

import com.beforejam.boards.domain.User;
import com.beforejam.boards.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void signUp(User user){
        User foundUser = userMapper.findByUsername(user.getUsername());

        if(Objects.nonNull(foundUser)){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        userMapper.save(user);
    }

    public User getUser(String username){
        return null;
    }
}
