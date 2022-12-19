package com.agropix.bacen.adapter.out.dto;

public interface TransacaoPixRequest {
    String getChaveOrigem();
    String getChaveDestino();
    String getBancoOrigem();
    String getValor();
}
