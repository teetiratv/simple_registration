package com.example.registration.enums;

public enum Error {
    BAD_REQUEST_CODE(400),
    SALARY_LESS_THAN_MESSAGE("Your salary is less than the specified amount."),
    USERNAME_IS_NULL_MESSAGE("Username Is Null"),
    USERNAME_IS_EMPTY_MESSAGE("Username Is Empty"),
    PASSWORD_IS_NULL_MESSAGE("Password Is Null"),
    PASSWORD_IS_EMPTY_MESSAGE("Password Is Empty"),
    SALARY_IS_NULL_MESSAGE("Salary Is Null"),
    SALARY_IS_EMPTY_MESSAGE("Salary Is Empty"),
    USERNAME_EXISTS_MESSAGE("Username Exists"),
    JWT_INVALID_MESSAGE("JWT invalid"),
    SOMETHING_WRONG_MESSAGE("Something Went Wrong");

    private Integer code;
    private String message;

    Error(String message) {
        this.message = message;
    }

    Error(Integer code) {
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
