package com.test.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;

@Aspect
@Component
public class LoggingAdvice {

    @Autowired
    private WebClient webClient;
    
    @Around("@within(Logging)")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable{
            ObjectMapper mapper = new ObjectMapper();
            Object object = point.proceed();
            String status = null;
            String url = null;
            String message = null;
            String method = null;

            String data = mapper.writeValueAsString(object);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            Map<String,Object> res = mapper.readValue(data,Map.class);

            String body = mapper.writeValueAsString(res.get("body"));
            status = res.get("statusCodeValue").toString();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                url = request.getRequestURL().toString();
                method = request.getMethod();
            }
            if(!status.equals("200")){
                Map<String,Object> bodyData = mapper.readValue(body,Map.class);
                message = bodyData.get("message").toString();
            }
                var log = creatLog("aop annotation",status,url,method,message);
                saveLog(log);
            return object;  
    }

    
   
    @AfterThrowing(pointcut = "@within(Logging)",throwing = "ex")
    public void exceptionLog(Exception ex){
            String status = null;
            String url = null;
            String message = null;
            String method = null;
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            status = "500";
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                url = request.getRequestURL().toString();
                method = request.getMethod();
            }
                message = ex.getMessage();
                var log = creatLog("exception aop annotation",status,url,method,message);
                saveLog(log);
            
    }





    private void saveLog(Log log){
        try {
            webClient.post()
                         .uri(new URI("http://localhost:8081/log/saveLog"))
                         .body(Mono.just(log),Log.class)
                         .retrieve()
                         .bodyToMono(String.class)
                         .block();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
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
