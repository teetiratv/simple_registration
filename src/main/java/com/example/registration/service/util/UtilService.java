package com.example.registration.service.util;

import com.example.registration.model.StandardResponseException;

public interface UtilService {
    String generateRefId(Long timestamp, String phone);

    String classifySalary(Double salary) throws StandardResponseException;
}
