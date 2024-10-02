package com.example.resp;

public class Resp {

    private int code;
    private String message;
    private Object data;

    public Resp() {
    }

    public Resp(RespCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Resp(RespCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public Resp setCode(RespCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        return this;
    }

    public int getCode() {
        return code;
    }

    public Resp setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Resp setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Resp setData(Object data) {
        this.data = data;
        return this;
    }

}
