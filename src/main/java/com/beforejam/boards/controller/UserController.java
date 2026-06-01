package com.beforejam.boards.controller;

import com.beforejam.boards.domain.User;
import com.beforejam.boards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody User user){
        System.out.println("Controller 진입");
        userService.signUp(user);

        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){

         return  null;
    }

}
