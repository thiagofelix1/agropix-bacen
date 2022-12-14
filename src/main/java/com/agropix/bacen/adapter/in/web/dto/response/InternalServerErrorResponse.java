package com.agropix.bacen.adapter.in.web.dto.response;


import lombok.Getter;

@Getter
public class InternalServerErrorResponse {
    private int statusCode;
    private String message;
    private long errorCode;

    public InternalServerErrorResponse(int statusCode, String message, long errorCode) {
        this.statusCode = statusCode;
        this.message = message;
        this.errorCode = errorCode;
    }

}
