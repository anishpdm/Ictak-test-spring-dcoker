package com.company.ictaktest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class Controller {

    private static final Map<String, String> tokenStore = new HashMap<>();


    @PostMapping("/data")
    public Map<String, String> secureData(
            HttpServletRequest request,
            @RequestBody Map<String, Object> requestBody) {

        // Get token from Authorization header
        String token = request.getHeader("Authorization");

        String data= (String) requestBody.get("uname");

        // Validate token
        if (token == null || ! tokenStore.containsKey(token)) {
            throw new RuntimeException("Unauthorized");
        }

        // Get the user associated with the token
        String user = tokenStore.get(token);

        return Map.of(
                "message", "Secure data accessed by " + user,
                "receivedData", requestBody.toString()  // Echo back received data
        );
    }



    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String,String> d) {
        System.out.println("Api Called");
        System.out.println(d.get("username"));
        if ("admin".equals(d.get("username")) && "password".equals(d.get("password"))) {
            String token = UUID.randomUUID().toString(); // Generate unique token
            tokenStore.put(token, d.get("username")); // Store in memory
            return Map.of("token", token);
        }
        throw new RuntimeException("Invalid credentials");
    }


    @GetMapping("/test")
    public String getTest(){

        return "hello";
    }


    @PostMapping("/demo")
    public String getDemo(){
        return "Test Demo";
    }



}
