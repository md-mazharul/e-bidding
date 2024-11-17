package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.User;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> setUser(@RequestBody User user) {
        if (!ObjectUtils.isEmpty(userService.createUser(user))) {
            return new ResponseEntity<>("user created !!", HttpStatus.OK);
        }
        return new ResponseEntity<>("user NOT created !!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



