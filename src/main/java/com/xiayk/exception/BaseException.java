package com.xiayk.exception;

public class BaseException extends RuntimeException{

    private int code;

    public BaseException(int code, String message){
        super(message);
        this.code = code;
    }

    public BaseException(String message){
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
