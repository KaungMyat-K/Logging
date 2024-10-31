package com.log.response;

import java.util.List;

import lombok.Data;

@Data
public class FilterItemResponse {
    
    private List<Object> status;
    private List<Object> method;
}
