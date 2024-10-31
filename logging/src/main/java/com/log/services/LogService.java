package com.log.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.log.entity.Log;
import com.log.repo.LogRepo;
import com.log.request.FilterRequest;
import com.log.response.FilterItemResponse;

@Service
public class LogService {
    
    @Autowired
    private LogRepo logRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Long generateId(){
        return (long)(Math.random()*1001);
    }

    public void save(Log log){
        log.setId(generateId());
        logRepo.save(log);
    }

    public List<Log> findAll(){
        return logRepo.findAll();
    }

    public List<Log> findByFilter(FilterRequest req){
        Criteria criteria = new Criteria()
        .andOperator(
                req.getStatusCode() != null && !req.getStatusCode().isEmpty() ? Criteria.where("statusCode").is(req.getStatusCode()) : new Criteria(),
                req.getMethod() != null && !req.getMethod().isEmpty() ? Criteria.where("method").is(req.getMethod()) : new Criteria(),
                ((req.getStartDate() != null && !req.getStartDate().isEmpty()) && (req.getEndDate() != null && !req.getEndDate().isEmpty())) ? 
                    Criteria.where("date").gte(req.getStartDate()).lte(req.getEndDate()) :
                    (req.getStartDate() != null && !req.getStartDate().isEmpty() ? Criteria.where("date").gte(req.getStartDate()) : 
                    (req.getEndDate() != null && !req.getEndDate().isEmpty() ? Criteria.where("date").lte(req.getEndDate()) : new Criteria())
                )
            );
            System.out.println(req.getStartDate() + " "+ req.getEndDate().isEmpty());
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Log.class);
    }

    public FilterItemResponse findFilterItem(String projectName){
        Query statusQuery = new Query();
        statusQuery.fields().include("statusCode");
        Query methodQuery = new Query();
        methodQuery.fields().include("method");

        FilterItemResponse response = new FilterItemResponse();
        response.setMethod(mongoTemplate.findDistinct(statusQuery, "statusCode", projectName, Object.class));
        response.setStatus(mongoTemplate.findDistinct(methodQuery, "method", projectName, Object.class));
        return response;
    } 

    
}
