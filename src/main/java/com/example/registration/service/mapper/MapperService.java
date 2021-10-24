package com.example.registration.service.mapper;

import com.example.registration.entity.Person;

import java.util.Map;

public interface MapperService {
    Person toPerson(Map<String, Object> body);
}
