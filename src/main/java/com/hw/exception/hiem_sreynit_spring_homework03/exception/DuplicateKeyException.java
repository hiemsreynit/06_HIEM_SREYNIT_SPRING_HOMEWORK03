package com.hw.exception.hiem_sreynit_spring_homework03.exception;


public class DuplicateKeyException extends RuntimeException{
    public DuplicateKeyException (String message) {
        super(message);
    }
}
