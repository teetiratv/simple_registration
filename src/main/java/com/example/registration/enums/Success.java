package com.example.registration.enums;

public enum Success {
    SUCCESS_CODE(200),
    SUCCESS_MESSAGE("Success");

    private Integer code;
    private String message;

    Success(String message) {
        this.message = message;
    }

    Success(Integer code) {
        this.code = code;
    }

    public Integer getCode() {

        return code;
    }

    @Override
    public String toString() {
        return message;
    }
}
