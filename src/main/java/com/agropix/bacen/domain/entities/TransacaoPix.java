package com.agropix.bacen.domain.entities;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransacaoPix {

    private ChavePix chaveOrigem;
    private ChavePix chaveDestino;
    private BigDecimal valor;

}
