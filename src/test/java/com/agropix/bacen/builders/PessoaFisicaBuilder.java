package com.agropix.bacen.builders;

import com.agropix.bacen.domain.entities.PessoaFisica;

public class PessoaFisicaBuilder {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    PessoaFisicaBuilder() {  }

    public static PessoaFisicaBuilder builder() {
        return new PessoaFisicaBuilder();
    }

    public PessoaFisicaBuilder withNome(String nome) {
        this.nome = nome;

        return this;
    }

    public PessoaFisicaBuilder withCpf(String cpf) {
        this.cpf = cpf;

        return this;
    }

    public PessoaFisicaBuilder withTelefone(String telefone) {
        this.telefone = telefone;

        return this;
    }

    public PessoaFisicaBuilder withEmail(String email) {
        this.email = email;

        return this;
    }

    public PessoaFisica build() {
        return new PessoaFisica(this.nome, this.cpf, this.email, this.telefone);
    }
}
