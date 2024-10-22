package com.test.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

@Component
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
            if (response.getStatus() == 500) {
                responseBody(request,response);
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

    private void responseBody(HttpServletRequest request,HttpServletResponse response){
        try {
        // HttpClient client = HttpClient.newHttpClient();
        // HttpRequest req = HttpRequest.newBuilder()
        //     .uri(URI.create(request.getRequestURL().toString()))
        //     .build();
        //     HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        //     String responseBody = res.body();
        //     System.out.println("Response body: " + res.body());
        //     ObjectMapper objectMapper = new ObjectMapper();
        //     JsonNode jsonNode = objectMapper.readTree(responseBody);
        //     System.out.println("json node : "+ jsonNode);
        //     String fieldValue = jsonNode.get("message").asText();
        //     System.out.println("this is field value : "+fieldValue);

        HttpURLConnection connection = (HttpURLConnection) new URL(request.getRequestURL().toString()).openConnection();
            InputStream inputStream = connection.getInputStream();
            System.out.println("this is >> "+inputStream);
        } catch (Exception e) {
            e.getStackTrace();
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
