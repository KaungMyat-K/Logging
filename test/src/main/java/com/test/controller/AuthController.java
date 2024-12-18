package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.test.request.AuthenticationRequest;
import com.test.response.AuthenticationResponse;
import com.test.service.AuthenticationService;
import com.test.utils.Logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Logging
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> logIn(@RequestBody AuthenticationRequest request) {
            return new ResponseEntity<>(authenticationService.authenticate(request),HttpStatus.OK);
          
    }
    

}
