package com.example.registration.service.mapper;

import com.example.registration.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MapperServiceTest {
    @InjectMocks
    private MapperServiceImpl mapperService;

    @Test
    public void toPerson_Success() {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "firstnameTest");
        body.put("lastname", "lastnameTest");
        body.put("age", 19);
        body.put("salary", Double.parseDouble("200000"));
        body.put("phone", "phoneTest");
        body.put("address", "addressTest");
        body.put("username", "usernameTest");

        //When
        Person resPerson = mapperService.toPerson(body);

        //Then
        Person expectPerson = new Person();
        expectPerson.setFirstname("firstnameTest");
        expectPerson.setLastname("lastnameTest");
        expectPerson.setAge(19);
        expectPerson.setSalary(Double.parseDouble("200000"));
        expectPerson.setPhone("phoneTest");
        expectPerson.setAddress("addressTest");
        assertEquals(expectPerson, resPerson);
    }

    @Test
    public void toPerson_AllDataIsNull_Success() {
        //Given
        Map<String, Object> body = new HashMap<>();

        //When
        Person resPerson = mapperService.toPerson(body);

        //Then
        Person expectPerson = new Person();
        assertEquals(expectPerson, resPerson);
    }
}
