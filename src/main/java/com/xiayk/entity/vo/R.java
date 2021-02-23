package com.xiayk.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xiayk.exception.BaseException;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;

public class R<T> implements Serializable {

    public static final int SUCCESSFUL_CODE = 200;
    public static final String SUCCESSFUL_message = "处理成功";

    private int code;
    private String message;
//    @JsonIgnore
//    @JsonProperty("http_date")
    private Instant time;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public R() {
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param message
     * @param data
     */
    private R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    public R message(String message){
        this.message = message;
        return this;
    }

    public R code(int code){
        this.code = code;
        return this;
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static R success(Object data) {
        return new R<>(SUCCESSFUL_CODE, SUCCESSFUL_message, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static R success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    public static R fail(BaseException baseException) {
        return new R(baseException.getCode(), baseException.getMessage(), null);
    }

    public static R fail() {
        return new R(500, "处理异常，请稍后再试", null);
    }

    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE == (this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }


    public String toJsonString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":").append(code);
        if (message != null) {
            sb.append(",\"message\":\"").append(message).append('\"');
        }
        if (time != null) {
            sb.append(",\"time\":").append("\"").append(time).append("\"");
        }
        if (data != null) {
            sb.append(", \"data\":").append(data);
        }
        sb.append('}');
        return sb.toString();
    }
}
