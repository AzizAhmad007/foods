package com.test.food.controller;

import com.test.food.dto.MstUserDto;
import com.test.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity createUser(@RequestBody MstUserDto request) {
        return userService.createUser(request);
    }

    @PostMapping("/update")
    public ResponseEntity updateUser(@RequestBody MstUserDto request){
        return userService.updateUser(request);
    }
}
