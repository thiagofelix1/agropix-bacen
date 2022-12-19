package com.agropix.bacen.adapter.in.web.dto.request.transacao;

import com.agropix.bacen.domain.entities.TransacaoPix;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransacaoRequest {
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
}
