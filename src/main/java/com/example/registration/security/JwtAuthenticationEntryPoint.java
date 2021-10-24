package com.example.registration.security;

import com.example.registration.model.Response;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.registration.enums.Error.BAD_REQUEST_CODE;
import static com.example.registration.enums.Error.JWT_INVALID_MESSAGE;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static Gson gson = new Gson();

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {

        httpServletResponse.setStatus(BAD_REQUEST_CODE.getCode());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(
                gson.toJson(new Response(BAD_REQUEST_CODE.getCode(), JWT_INVALID_MESSAGE.toString()))
        );

    }
}