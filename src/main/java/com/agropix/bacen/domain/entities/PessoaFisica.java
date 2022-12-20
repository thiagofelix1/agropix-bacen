package com.agropix.bacen.domain.entities;

import com.agropix.bacen.domain.utils.Utils;
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
        this.cpf = Utils.removeCaracteres(cpf);
        this.email = email;
        this.telefone = telefone;

        this.validar();
    }

    private void validar(){

    }
}
