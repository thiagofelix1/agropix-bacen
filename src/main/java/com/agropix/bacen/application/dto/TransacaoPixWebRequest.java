package com.agropix.bacen.application.dto;

import com.agropix.bacen.adapter.out.dto.TransacaoPixRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoPixWebRequest implements TransacaoPixRequest {
    private String chaveOrigem;
    private String chaveDestino;
    private String bancoOrigem;
    private String valor;
}
