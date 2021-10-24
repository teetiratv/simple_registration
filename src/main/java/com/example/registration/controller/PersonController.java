package com.example.registration.controller;

import com.example.registration.model.Response;
import com.example.registration.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Response getAllPerson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return personService.getAllPerson(request, response);
    }
}
