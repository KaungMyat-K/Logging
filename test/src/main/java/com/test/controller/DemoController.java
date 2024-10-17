package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/demo")
public class DemoController {
     
    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("hello from end point");
    }
    
}
