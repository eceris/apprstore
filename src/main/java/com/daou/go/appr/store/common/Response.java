package com.daou.go.appr.store.common;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty("code")
    String resultCode;

    @JsonProperty("message")
    String message;

    public Response(HttpStatus httpStatus) {
        super();
        this.resultCode = String.valueOf(httpStatus.value());
    }

    public Response(HttpStatus httpStatus, String message) {
        super();
        this.resultCode = String.valueOf(httpStatus.value());
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(HttpStatus httpStatus) {
        this.resultCode = String.valueOf(httpStatus.value());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
