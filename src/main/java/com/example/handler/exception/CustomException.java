package com.example.handler.exception;

/**
 * @author garyssu
 */
public class CustomException extends RuntimeException {

    private final int code;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
