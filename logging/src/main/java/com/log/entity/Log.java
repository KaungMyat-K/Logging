package com.log.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "log")
@JsonIgnoreProperties(value = {
        "id"
},allowGetters = true)
public class Log {
    
    @Id
    private Long id;
    private String name;
    private String message;
    private String statusCode;
    private String date;
    private String url;
    private String method;
}
