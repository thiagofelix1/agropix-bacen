package com.agropix.bacen.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoResponse {
    private String chaveOrigem;
    private String chaveDestino;
    private String valor;
    private String banco;
}
