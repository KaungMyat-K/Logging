package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.request.RegisterRequest;
import com.test.response.AuthenticationResponse;
import com.test.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> postMethodName(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.saveUser(request));
    }
    
}
