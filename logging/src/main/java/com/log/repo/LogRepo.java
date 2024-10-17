package com.log.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.log.entity.Log;

@Repository
public interface LogRepo extends MongoRepository<Log,Long>  {
    
}
