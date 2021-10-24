package com.example.registration.service.util;

import com.example.registration.enums.Classify;
import com.example.registration.model.StandardResponseException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import static com.example.registration.enums.Error.BAD_REQUEST_CODE;
import static com.example.registration.enums.Error.SALARY_LESS_THAN_MESSAGE;

@Service
public class UtilServiceImpl implements UtilService {

    @Override
    public String generateRefId(Long timestamp, String phone) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        if (phone.length() < 4) {
            return format.format(timestamp);
        }
        return format.format(timestamp) + subStringPhoneLast4Digits(phone);
    }

    private String subStringPhoneLast4Digits(String phone) {
        return phone.substring(phone.length() - 4);
    }

    @Override
    public String classifySalary(Double salary) throws StandardResponseException {
        if (salary > 50000) {
            return Classify.PLATINUM.toString();
        } else if (salary >= 30000) {
            return Classify.GOLD.toString();
        } else if (salary >= 15000) {
            return Classify.SILVER.toString();
        } else {
            throw new StandardResponseException(
                    BAD_REQUEST_CODE.getCode()
                    , BAD_REQUEST_CODE.getCode()
                    , SALARY_LESS_THAN_MESSAGE.toString());
        }
    }
}
