package com.agropix.bacen.adapter.in.web.dto.request.criacao_chave;

import com.agropix.bacen.domain.entities.Banco;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.Conta;
import com.agropix.bacen.domain.entities.PessoaFisica;
import com.agropix.bacen.domain.enums.TipoChavePix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriacaoChavePixRequest {
    private String chave;
    private BancoData banco;
    private TipoChavePix tipo;
    private TitularData titular;
    private ContaData conta;

    public static ChavePix toEntity(CriacaoChavePixRequest request) {

        return new ChavePix(request.getChave(),
            new Banco(request.banco.getNome(), request.banco.getCodigo()),
            request.tipo,
            new Conta(request.conta.getNumero(), request.conta.getDigito(),
                new PessoaFisica(request.titular.getNome(), request.titular.getCpf(), request.titular.getEmail(), request.titular.getTelefone()))
        );
    }

}
