package com.example.registration.service.auth;

import com.example.registration.entity.Person;
import com.example.registration.model.Response;
import com.example.registration.model.StandardResponseException;
import com.example.registration.repository.PersonRepository;
import com.example.registration.security.JwtTokenProvider;
import com.example.registration.service.mapper.MapperService;
import com.example.registration.service.util.UtilService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.example.registration.enums.Classify.PLATINUM;
import static com.example.registration.enums.Error.*;
import static com.example.registration.enums.Success.SUCCESS_CODE;
import static com.example.registration.enums.Success.SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class AuthServiceTest {
    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private PersonRepository personRepository;
    @Mock
    private UtilService utilService;
    @Mock
    private MapperService mapperService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider jwtTokenProvider;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void register_Error_USERNAME_IS_NULL() {
        //Given
        Map<String, Object> body = new HashMap<>();

        //When
        try {
            Response resRegister = authService.register(body, request, response);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , USERNAME_IS_NULL_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    @Test
    public void register_Error_USERNAME_IS_EMPTY() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "");

        //When
        try {
            Response resRegister = authService.register(body, request, response);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , USERNAME_IS_EMPTY_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    @Test
    public void register_Error_PASSWORD_IS_NULL() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "1");

        //When
        try {
            Response resRegister = authService.register(body, request, response);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , PASSWORD_IS_NULL_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    @Test
    public void register_Error_PASSWORD_IS_EMPTY() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "1");
        body.put("password", "");

        //When
        try {
            Response resRegister = authService.register(body, request, response);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , PASSWORD_IS_EMPTY_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    @Test
    public void register_Error_SALARY_IS_NULL() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "usernameTest");
        body.put("password", "1");

        //When
        try {
            Response resRegister = authService.register(body, request, response);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , SALARY_IS_NULL_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    @Test
    public void register_Error_SALARY_IS_EMPTY() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "usernameTest");
        body.put("password", "1");
        body.put("salary", "");

        //When
        try {
            Response resRegister = authService.register(body, request, response);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , SALARY_IS_EMPTY_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    @Test
    public void register_Error_USERNAME_EXISTS() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "usernameTest");
        body.put("password", "1");
        body.put("salary", Double.parseDouble("12312"));

        //When
        when(personRepository.existsByUsername(anyString())).thenReturn(true);

        try {
            Response resRegister = authService.register(body, request, response);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , USERNAME_EXISTS_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    @Test
    public void register_Error_SALARY_LESS_THAN() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "usernameTest");
        body.put("password", "1");
        body.put("salary", Double.parseDouble("12312"));
        Person person = new Person();

        //When
        when(personRepository.existsByUsername(anyString())).thenReturn(false);
        when(mapperService.toPerson(any())).thenReturn(person);
        when(utilService.classifySalary(anyDouble())).thenThrow(new StandardResponseException(
                BAD_REQUEST_CODE.getCode()
                , BAD_REQUEST_CODE.getCode()
                , SALARY_LESS_THAN_MESSAGE.toString()));

        try {
            Response resRegister = authService.register(body, request, response);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , SALARY_LESS_THAN_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    @Test
    public void register_Success() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "usernameTest");
        body.put("password", "1");
        body.put("salary", Double.parseDouble("600000"));
        Person person = new Person();
        person.setUsername(body.get("username").toString());
        person.setPassword(body.get("password").toString());
        person.setSalary(Double.parseDouble(body.get("salary").toString()));

        //When
        when(personRepository.existsByUsername(anyString())).thenReturn(false);
        when(mapperService.toPerson(any())).thenReturn(person);
        when(utilService.classifySalary(anyDouble())).thenReturn(PLATINUM.toString());
        when(utilService.generateRefId(any(), any())).thenReturn("");
        when(passwordEncoder.encode(anyString())).thenReturn("xxxx");

        Response resRegister = authService.register(body, request, response);

        //Then
        Response expectResponse = new Response(SUCCESS_CODE.getCode(), SUCCESS_MESSAGE.toString());
        assertEquals(expectResponse, resRegister);
    }

    @Test
    public void authenticate_Success() throws Exception {
        //Given
        Map<String, Object> body = new HashMap<>();
        body.put("username", "usernameTest");
        body.put("password", "1");
        String token = "testToken";

        //When
        when(jwtTokenProvider.generateToken(any())).thenReturn(token);
        Response resRegister = authService.authenticate(body, request, response);

        //Then
        Map<String, Object> resData = new HashMap<>();
        resData.put("token", token);
        Response expectResponse = new Response(SUCCESS_CODE.getCode(), SUCCESS_MESSAGE.toString(), resData);
        assertEquals(expectResponse, resRegister);
    }
}
