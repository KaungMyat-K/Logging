package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/demo")
public class DemoController {
     
    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("hello from end point");
    }

    @GetMapping("/getException")
    public ResponseEntity<String> exception() {
       try {
            getException();
            return ResponseEntity.ok("exception endpoint");
        } catch (Exception e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
        }       
    }
    

    private void getException() throws Exception{
        throw new RuntimeException("smth getting wrong");
    }
}
