package com.test.utils;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Aspect
@Component
public class LoggingAdvice {

    @Autowired
    private WebClient webClient;

    @Pointcut(value = "execution(* com.test.controller.*.*(..) )")
    public void mypointcut(){

    }
    
    @Around("mypointcut()")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable{
            ObjectMapper mapper = new ObjectMapper();
            String methodname = point.getSignature().getName();
            String className =  point.getTarget().getClass().toString();
            Object[] array = point.getArgs();
            Object object = point.proceed();


            String data = mapper.writeValueAsString(object);
            
            Map<String,Object> res = mapper.readValue(data,Map.class);
            System.out.println("this is statusCodeValue : "+res.get("statusCode"));

            String body = mapper.writeValueAsString(res.get("body"));
            Map<String,Object> bodyData = mapper.readValue(body,Map.class);
            System.out.println("this is body : "+bodyData.get("message"));
            //Map<String,Object> bodydata = mapper.readValue(body,Map.class);
            // System.out.println("this is statusCodeValue : "+bodydata.get("message"));


            System.out.println("className : "+className+" , "+"methodName : "+methodname+" , "+"Request : "+mapper.writeValueAsString(array));
            System.out.println("className : "+className+" , "+"methodName : "+methodname+" , "+"Response : "+mapper.writeValueAsString(object));
            // var log = creatLog("AOP");
            // webClient.post()
            //          .uri(new URI("http://localhost:8081/log/saveLog"))
            //          .body(Mono.just(log),Log.class)
            //          .retrieve()
            //          .bodyToMono(String.class)
            //          .block();

            return object;
        
    }


    private Log creatLog(String name,String statusCode,String url,String method,String message){
        return Log.builder()
                .name(name)
                .message(message)
                .statusCode(statusCode)
                .date(LocalDateTime.now().toString())
                .url(url)
                .method(method)
                .build();
    }

}
