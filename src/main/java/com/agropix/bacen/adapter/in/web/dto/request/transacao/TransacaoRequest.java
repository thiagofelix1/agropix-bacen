package com.agropix.bacen.adapter.in.web.dto.request.transacao;

import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.TransacaoPix;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
public class TransacaoRequest {
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;

    public TransacaoPix toEntity(ChavePix chaveOrigem, ChavePix chaveDestino) {
        return new TransacaoPix(chaveOrigem, chaveDestino, new BigDecimal(valor));
    }
}
