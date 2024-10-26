package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.response.DefaultResponse;
import com.test.utils.Logging;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Logging
@RestController
@RequestMapping("/demo")
public class DemoController {
     
    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("hello from end point");
    }

    @GetMapping(value = "/getException",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> exception() {
       try {
            getException();
            return ResponseEntity.ok(DefaultResponse.builder().message("no error").build());
        } catch (Exception e) {
            e.getStackTrace();
            DefaultResponse res = DefaultResponse.builder().message("smth wrong!!!").build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }       
    }
    
    @GetMapping(value = "/onlyException",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onlyexception() {
            getException();
            return ResponseEntity.ok(DefaultResponse.builder().message("no error").build());           
    }

    private void getException() {
        throw new RuntimeException("smth getting wrong");
    }
}
