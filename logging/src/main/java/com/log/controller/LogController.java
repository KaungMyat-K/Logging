package com.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.entity.Log;
import com.log.request.FilterRequest;
import com.log.services.LogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/log")
public class LogController {
    
    @Autowired
    private LogService logService;

    @PostMapping("/saveLog")
    public ResponseEntity<Object> save(@RequestBody Log log) {
        logService.save(log);
        return ResponseEntity.ok("saved log successfully");
    }
    
    @GetMapping("/getByFilter")
    public ResponseEntity<Object> getLogByFilter(@RequestBody FilterRequest req) {
        return ResponseEntity.ok(logService.findByFilter(req));
    }
        
    @GetMapping("/getFilterItem/{project}")
    public ResponseEntity<Object> getFilterItem(@PathVariable String project) {
        return ResponseEntity.ok(logService.findFilterItem(project));
    }
}
