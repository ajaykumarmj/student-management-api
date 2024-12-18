package com.student.studentapi.exception;

import java.time.LocalDateTime;

public class StudentException {

    private final String message;
    private final LocalDateTime timestamp;
    private final int statusCode;

    public StudentException(String message, int statusCode) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
