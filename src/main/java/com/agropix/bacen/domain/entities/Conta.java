package com.agropix.bacen.domain.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Conta {
    private String numeroConta;
    private String digito;
    private PessoaFisica titular;

}
