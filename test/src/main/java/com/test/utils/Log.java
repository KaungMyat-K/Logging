package com.test.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Log {
    
    private String statusCode;
    private String date;
    private String url;
    private String method;
}
