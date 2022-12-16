package com.agropix.bacen.builders;

import com.agropix.bacen.domain.entities.Banco;

public class BancoBuilder {

    private String nome;
    private String codigo;

    BancoBuilder() {  }

    public static BancoBuilder builder() {
        return new BancoBuilder();
    }

    public BancoBuilder withNome(String nome) {
        this.nome = nome;

        return this;
    }

    public BancoBuilder withCodigo(String codigo) {
        this.codigo = codigo;

        return this;
    }

    public Banco build() {
        return new Banco(this.nome, this.codigo);
    }
}
