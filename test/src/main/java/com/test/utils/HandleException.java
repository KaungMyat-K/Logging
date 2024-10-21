package com.test.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class HandleException {

    @Autowired
    private WebClient webClient;

    @ExceptionHandler(Exception.class)
    public void handleException (HttpServletRequest request,HttpServletResponse response,Exception ex){

        if (ex instanceof Exception) {
            var log = creatLog(String.valueOf(response.getStatus()), request.getRequestURL().toString(),request.getMethod(),ex.getMessage());
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

    }

    private Log creatLog(String statusCode,String url,String method,String message){
        return Log.builder()
                .name("handleException")
                .message(message)
                .statusCode(statusCode)
                .date(LocalDateTime.now().toString())
                .url(url)
                .method(method)
                .build();
    }

}
