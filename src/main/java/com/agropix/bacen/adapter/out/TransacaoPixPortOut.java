package com.agropix.bacen.adapter.out;

import com.agropix.bacen.adapter.out.dto.FalhaTransacao;
import com.agropix.bacen.adapter.out.dto.TransacaoEfetuada;
import com.agropix.bacen.adapter.out.dto.TransacaoPixRequest;
import com.agropix.bacen.domain.type.either.Either;

public interface TransacaoPixPortOut {
    Either<FalhaTransacao, TransacaoEfetuada> enviarTransacao(String callbackUrl, TransacaoPixRequest request);
}
