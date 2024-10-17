package com.log.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.log.entity.Log;
import com.log.repo.LogRepo;

@Service
public class LogService {
    
    @Autowired
    private LogRepo logRepo;


    public void save(Log log){
        log.setId(generateId());
        logRepo.save(log);
    }

    public List<Log> findAll(){
        return logRepo.findAll();
    }

    private Long generateId(){
        return (long)(Math.random()*1001);
    }

}
