package com.example.registration.model;

import lombok.Data;

@Data
public class StandardResponseException extends Exception {
    private Response response;
    private Integer httpCode;

    public StandardResponseException(Integer httpCode, Integer code, String message) {
        this.httpCode = httpCode;
        this.response = new Response(code, message);
    }
}
