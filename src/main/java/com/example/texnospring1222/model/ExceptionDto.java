package com.example.texnospring1222.model;

public class ExceptionDto {
    private String message;

    public ExceptionDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
