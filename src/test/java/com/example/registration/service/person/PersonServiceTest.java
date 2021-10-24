package com.example.registration.service.person;

import com.example.registration.entity.Person;
import com.example.registration.model.Response;
import com.example.registration.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.example.registration.enums.Success.SUCCESS_CODE;
import static com.example.registration.enums.Success.SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class PersonServiceTest {
    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void getAllPerson() {
        //Given
        Person person = new Person();
        person.setUsername("test");
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        //When
        when(personRepository.findAll()).thenReturn(personList);

        Response resRegister = personService.getAllPerson(request, response);

        //Then
        Response expectResponse = new Response(SUCCESS_CODE.getCode(), SUCCESS_MESSAGE.toString(), personList);
        assertEquals(expectResponse, resRegister);
    }
}
