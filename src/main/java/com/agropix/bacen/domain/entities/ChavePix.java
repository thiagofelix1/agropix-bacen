package com.agropix.bacen.domain.entities;

import com.agropix.bacen.domain.enums.TipoChavePix;
import com.agropix.bacen.domain.exceptions.ChavePixInvalidaException;
import com.agropix.bacen.domain.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.function.Predicate;

@Getter
@Setter
public class ChavePix {
    private String chave;
    private Banco banco;
    private TipoChavePix tipo;
    private Conta conta;

    protected ChavePix() {  }

    public ChavePix(String chave, Banco banco, TipoChavePix tipo, Conta conta) {
        this.chave = chave;
        this.banco = banco;
        this.tipo = tipo;
        this.conta = conta;

        this.validar();
    }

    private void validar() {

        var validators = new HashMap<TipoChavePix, Predicate<String>>();
        validators.put(TipoChavePix.CPF, Utils::validaCpf);
        validators.put(TipoChavePix.CELULAR, Utils::validaCelular);
        validators.put(TipoChavePix.EMAIL, Utils::validaEmail);
        validators.put(TipoChavePix.ALEATORIA, Utils::validaUUID);

        if (!validators.get(tipo).test(chave)) {
            throw new ChavePixInvalidaException(String.format("A chave %s nao e valida para o tipo %s", chave, tipo));
        }
    }
}
