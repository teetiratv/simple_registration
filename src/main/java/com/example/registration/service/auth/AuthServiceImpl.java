package com.example.registration.service.auth;

import com.example.registration.entity.Person;
import com.example.registration.model.Response;
import com.example.registration.model.StandardResponseException;
import com.example.registration.repository.PersonRepository;
import com.example.registration.security.JwtTokenProvider;
import com.example.registration.service.mapper.MapperService;
import com.example.registration.service.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.registration.enums.Error.*;
import static com.example.registration.enums.Success.SUCCESS_CODE;
import static com.example.registration.enums.Success.SUCCESS_MESSAGE;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private MapperService mapperService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UtilService utilService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Response register(Map<String, Object> body, HttpServletRequest request, HttpServletResponse response)
            throws StandardResponseException {

        this.isNullThrowStandardResponseException(
                body.get("username")
                , BAD_REQUEST_CODE.getCode()
                , BAD_REQUEST_CODE.getCode()
                , USERNAME_IS_NULL_MESSAGE.toString());
        this.isEmptyThrowStandardResponseException(
                body.get("username")
                , BAD_REQUEST_CODE.getCode()
                , BAD_REQUEST_CODE.getCode()
                , USERNAME_IS_EMPTY_MESSAGE.toString());

        this.isNullThrowStandardResponseException(
                body.get("password")
                , BAD_REQUEST_CODE.getCode()
                , BAD_REQUEST_CODE.getCode()
                , PASSWORD_IS_NULL_MESSAGE.toString());
        this.isEmptyThrowStandardResponseException(
                body.get("password")
                , BAD_REQUEST_CODE.getCode()
                , BAD_REQUEST_CODE.getCode()
                , PASSWORD_IS_EMPTY_MESSAGE.toString());

        this.isNullThrowStandardResponseException(
                body.get("salary")
                , BAD_REQUEST_CODE.getCode()
                , BAD_REQUEST_CODE.getCode()
                , SALARY_IS_NULL_MESSAGE.toString());
        this.isEmptyThrowStandardResponseException(
                body.get("salary")
                , BAD_REQUEST_CODE.getCode()
                , BAD_REQUEST_CODE.getCode()
                , SALARY_IS_EMPTY_MESSAGE.toString());

        if (personRepository.existsByUsername(body.get("username").toString())) {
            throw new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , USERNAME_EXISTS_MESSAGE.toString());
        }

        Long currentTime = System.currentTimeMillis();
        Person person = mapperService.toPerson(body);
        person.setUsername(body.get("username").toString().trim());
        person.setCreatedAt(currentTime);
        person.setMemberType(utilService.classifySalary(Double.parseDouble(body.get("salary").toString())));
        person.setRefId(utilService.generateRefId(currentTime, person.getPhone()));
        person.setPassword(passwordEncoder.encode(body.get("password").toString().trim()));

        personRepository.save(person);

        return new Response(SUCCESS_CODE.getCode(), SUCCESS_MESSAGE.toString());
    }

    @Override
    public Response authenticate(Map<String, Object> body, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.get("username"),
                        body.get("password")
                )
        );

        Map<String, Object> res = new HashMap<>();
        res.put("token", jwtTokenProvider.generateToken(authentication));

        return new Response(SUCCESS_CODE.getCode(), SUCCESS_MESSAGE.toString(), res);
    }

    private Boolean isNullThrowStandardResponseException(Object obj, Integer code, Integer httpCode, String message)
            throws StandardResponseException {
        if (Objects.isNull(obj)) {
            throw new StandardResponseException(code, httpCode, message);
        }
        return true;
    }

    private Boolean isEmptyThrowStandardResponseException(Object obj, Integer code, Integer httpCode, String message)
            throws StandardResponseException {
        if (obj.toString().isEmpty()) {
            throw new StandardResponseException(code, httpCode, message);
        }
        return true;
    }
}
