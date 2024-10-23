package com.test.utils;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

//@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private WebClient webClient;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       
        try {
           
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       
        try {
           
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       
        try {
            if (ex != null) {
                System.out.println("there is an error!!");
            }
            
            System.out.println("3 - afterCompletionHandle()");
            var log = creatLog("interceptor : afterCompletion",String.valueOf(response.getStatus()), request.getRequestURL().toString(),request.getMethod(),"");
            webClient.post()
                     .uri(new URI("http://localhost:8081/log/saveLog"))
                     .body(Mono.just(log),Log.class)
                     .retrieve()
                     .bodyToMono(String.class)
                     .block();
        }
        catch (Exception e) {
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
