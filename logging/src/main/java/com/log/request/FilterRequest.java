package com.log.request;

import lombok.Data;

@Data
public class FilterRequest {
    
    private String projectName;
    private String statusCode;
    private String startDate;
    private String endDate;
    private String method;

}
