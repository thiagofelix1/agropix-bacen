package com.agropix.bacen.builders;

import com.agropix.bacen.domain.entities.Conta;
import com.agropix.bacen.domain.entities.PessoaFisica;

public class ContaBuilder {

    private String numeroConta;
    private String digito;
    private PessoaFisica titular;

    ContaBuilder() {  }

    public static ContaBuilder builder() {
        return new ContaBuilder();
    }

    public ContaBuilder withNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;

        return this;
    }

    public ContaBuilder withDigito(String digito) {
        this.digito = digito;

        return this;
    }

    public ContaBuilder withTitular(PessoaFisica titular) {
        this.titular = titular;

        return this;
    }

    public Conta build() {
        return new Conta(this.numeroConta, this.digito, this.titular);
    }

}
