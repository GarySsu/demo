package com.example.resp;


public enum RespCode {

    SUCCESS(200, "SUCCESS"),
    FAILED(400, "ERROR"),
    INTERNAL_SERVER_ERROR(500, "an unexpected error occurred."),

    USERNAME_NOT_ALLOWED(1001, "username does not allowed"),

    PRODUCT_NOT_EXIST(2001,"product does not exist"),
    QUANTITY_NOT_ENOUGH(2002, "quantity does not enough");

    private int code;
    private String message;

    RespCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
