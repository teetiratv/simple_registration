package com.example.registration.enums;

public enum Classify {
    PLATINUM("Platinum"),
    GOLD("Gold"),
    SILVER("Silver");

    private String message;

    Classify(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
