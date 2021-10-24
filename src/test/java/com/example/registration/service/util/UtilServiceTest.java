package com.example.registration.service.util;


import com.example.registration.enums.Classify;
import com.example.registration.model.StandardResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;

import static com.example.registration.enums.Error.BAD_REQUEST_CODE;
import static com.example.registration.enums.Error.SALARY_LESS_THAN_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UtilServiceTest {
    @InjectMocks
    private UtilServiceImpl utilService;

    private SimpleDateFormat format;

    @BeforeEach
    public void init() {
        format = new SimpleDateFormat("yyyyMMdd");
    }

    @Test
    public void generateRefId_Success() {
        //Given
        Long timestamp = System.currentTimeMillis();
        String phone = "0800000000";

        //When
        String resRefId = utilService.generateRefId(timestamp, phone);

        //Then
        String expectRefId = format.format(timestamp) + subStringPhoneLast4Digits(phone);
        assertEquals(expectRefId, resRefId);
    }

    @Test
    public void generateRefId_PhoneLengthLessThan4_Error() {
        //Given
        Long timestamp = System.currentTimeMillis();
        String phone = "080";

        //When
        String resRefId = utilService.generateRefId(timestamp, phone);

        //Then
        String expectRefId = format.format(timestamp);
        assertEquals(expectRefId, resRefId);
    }

    @Test
    public void classifySalary_GivenSalary60k_Success_PLATINUM() throws StandardResponseException {
        //Given
        Double salary = Double.valueOf(60000);

        //When
        String resClassifySalary = utilService.classifySalary(salary);

        //Then
        String expectClassifySalary = Classify.PLATINUM.toString();
        assertEquals(expectClassifySalary, resClassifySalary);
    }

    @Test
    public void classifySalary_GivenSalary30k_Success_GOLD() throws StandardResponseException {
        //Given
        Double salary = Double.valueOf(30000);

        //When
        String resClassifySalary = utilService.classifySalary(salary);

        //Then
        String expectClassifySalary = Classify.GOLD.toString();
        assertEquals(expectClassifySalary, resClassifySalary);
    }

    @Test
    public void classifySalary_GivenSalary15k_Success_SILVER() throws StandardResponseException {
        //Given
        Double salary = Double.valueOf(15000);

        //When
        String resClassifySalary = utilService.classifySalary(salary);

        //Then
        String expectClassifySalary = Classify.SILVER.toString();
        assertEquals(expectClassifySalary, resClassifySalary);
    }

    @Test
    public void classifySalary_LessThan15k_Error_SALARY_LESS_THAN() {
        //Given
        Double salary = Double.valueOf(1);

        //When
        try {
            String resClassifySalary = utilService.classifySalary(salary);
        } catch (StandardResponseException e) {
            //Then
            StandardResponseException expectStandardResponseException = new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , SALARY_LESS_THAN_MESSAGE.toString());
            assertEquals(expectStandardResponseException, e);
        }
    }

    private String subStringPhoneLast4Digits(String phone) {
        return phone.substring(phone.length() - 4);
    }
}
