package com.agropix.bacen.domain.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PessoaFisica {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public PessoaFisica(String nome, String cpf, String email, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;

        this.validar();
    }

    private void validar(){
    }
}
