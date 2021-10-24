package com.example.registration.service.auth;

import com.example.registration.model.Response;
import com.example.registration.model.StandardResponseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AuthService {
    Response register(Map<String, Object> body, HttpServletRequest request, HttpServletResponse response)
            throws StandardResponseException;

    Response authenticate(Map<String, Object> body, HttpServletRequest request, HttpServletResponse response);
}
