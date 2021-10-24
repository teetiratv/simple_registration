package com.example.registration.service.mapper;

import com.example.registration.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class MapperServiceImpl implements MapperService {
    @Override
    public Person toPerson(Map<String, Object> body) {
        Person person = new Person();
        person.setFirstname(Objects.nonNull(body.get("firstname")) ? body.get("firstname").toString() : null);
        person.setLastname(Objects.nonNull(body.get("lastname")) ? body.get("lastname").toString() : null);
        person.setAge(Objects.nonNull(body.get("age")) ? Integer.parseInt(body.get("age").toString()) : null);
        person.setSalary(Objects.nonNull(body.get("salary")) ? Double.parseDouble(body.get("salary").toString()) : null);
        person.setPhone(Objects.nonNull(body.get("phone")) ? body.get("phone").toString() : null);
        person.setAddress(Objects.nonNull(body.get("address")) ? body.get("address").toString() : null);
        return person;
    }
}
