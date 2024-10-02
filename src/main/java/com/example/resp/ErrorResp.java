package com.example.resp;

import lombok.Data;

@Data
public class ErrorResp {

    private int code;
    private String message;

    public ErrorResp(String message) {
        this.message = message;
    }

    public ErrorResp(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
