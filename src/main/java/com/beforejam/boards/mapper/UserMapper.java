package com.beforejam.boards.mapper;

import com.beforejam.boards.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void save (User user);

    User findByUsername(String username);
}
