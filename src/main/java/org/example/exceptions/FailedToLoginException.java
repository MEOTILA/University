package org.example.exceptions;

public class FailedToLoginException extends RuntimeException{
    public FailedToLoginException(String message) {
        super(message);
    }
}
