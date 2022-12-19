package com.agropix.bacen.domain.entities;

import com.agropix.bacen.domain.enums.TransacaoPixStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
