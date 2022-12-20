package com.agropix.bacen.domain.entities;
import com.agropix.bacen.domain.enums.PortabilidadePixStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PortabilidadeChavePix {

    private String valorChave;
    private PortabilidadePixStatus status;
    private LocalDateTime dataSolicitacao;
    private Banco bancoTitular;
    private Banco bancoRequerente;

}
