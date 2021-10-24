package com.example.registration.service.person;

import com.example.registration.model.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PersonService {
    Response getAllPerson(HttpServletRequest request, HttpServletResponse response);
}
