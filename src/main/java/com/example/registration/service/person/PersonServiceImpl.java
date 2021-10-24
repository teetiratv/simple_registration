package com.example.registration.service.person;

import com.example.registration.model.Response;
import com.example.registration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.registration.enums.Success.SUCCESS_CODE;
import static com.example.registration.enums.Success.SUCCESS_MESSAGE;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Response getAllPerson(HttpServletRequest request, HttpServletResponse response) {
        return new Response(SUCCESS_CODE.getCode(), SUCCESS_MESSAGE.toString(), personRepository.findAll());
    }
}
