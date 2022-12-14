package com.agropix.bacen.adapter.in.web.dto.response;

import lombok.Getter;

@Getter
public class UnprossableEntityResponse {
    private String mensagem;
    private int statusCode;

    public UnprossableEntityResponse(String mensagem, int statusCode) {
        this.mensagem = mensagem;
        this.statusCode = statusCode;
    }
}
