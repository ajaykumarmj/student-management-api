package com.student.studentapi.exception;

public class MissingInputFieldException extends RuntimeException {

    public MissingInputFieldException(String message) {
        super(message);
    }
}
