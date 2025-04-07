package com.todoBackend.exception;

import java.util.Map;

public class ErrorResponse {
    private String code;
    private String message;
    private int status;
    private Map<String, String> errors;

    public ErrorResponse(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public ErrorResponse(String code, String message, int status, Map<String, String> errors) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
