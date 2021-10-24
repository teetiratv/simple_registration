package com.example.registration.controller;

import com.example.registration.model.Response;
import com.example.registration.model.StandardResponseException;
import com.example.registration.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public Response register(@RequestBody Map<String, Object> requestBody
            , HttpServletRequest request
            , HttpServletResponse response) throws StandardResponseException {
        return authService.register(requestBody, request, response);
    }

    @PostMapping("/signin")
    public Response login(@RequestBody Map<String, Object> requestBody
            , HttpServletRequest request
            , HttpServletResponse response) {
        return authService.authenticate(requestBody, request, response);
    }

}
