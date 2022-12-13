package com.agropix.bacen.domain.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PessoaFisica {

    private Long id;
    private String nome;
    private Long cpf;
    private String email;
    private Long telefone;

    public PessoaFisica(Long id, String nome, Long cpf, String email, Long telefone){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;

        this.validar();
    }

    private void validar(){
    }
}
