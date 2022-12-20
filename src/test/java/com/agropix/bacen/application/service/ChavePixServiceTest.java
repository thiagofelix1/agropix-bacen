package com.agropix.bacen.application.service;

import com.agropix.bacen.adapter.in.web.dto.request.criacao_chave.BancoData;
import com.agropix.bacen.adapter.in.web.dto.request.criacao_chave.ContaData;
import com.agropix.bacen.adapter.in.web.dto.request.criacao_chave.CriacaoChavePixRequest;
import com.agropix.bacen.adapter.in.web.dto.request.criacao_chave.TitularData;
import com.agropix.bacen.adapter.out.DatabasePortOutPostgreSql;
import com.agropix.bacen.domain.entities.Banco;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.Conta;
import com.agropix.bacen.domain.entities.PessoaFisica;
import com.agropix.bacen.domain.enums.TipoChavePix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ChavePixServiceTest {

    @InjectMocks
    private ChavePixService chavePixService;

    @Mock
    private DatabasePortOutPostgreSql databasePortOutPostgreSql;

    private ChavePix fakeChavePix;
    private CriacaoChavePixRequest criacaoChavePixRequest;

    @BeforeEach
    void setup() {
        String chaveAleatoria = UUID.randomUUID().toString();
        Banco banco = new Banco("Itaú", "1515");
        BancoData bancoData = new BancoData("Itaú", "1515");
        TipoChavePix tipoChavePix = TipoChavePix.ALEATORIA;
        PessoaFisica pessoaFisica = new PessoaFisica("Thiago felix da silva", "11111111111", "thiagofelixdasilva099@gmail.com", "+5592988754654");
        Conta conta = new Conta("1515", "02", pessoaFisica);
        ContaData contaData = new ContaData("1515", "02");
        TitularData titularData = new TitularData("Thiago felix da silva", "11111111111", "thiagofelixdasilva099@gmail.com", "+5592988754654");
        criacaoChavePixRequest = new CriacaoChavePixRequest(chaveAleatoria, bancoData, tipoChavePix, titularData, contaData);
//       chaveAleatoria, banco, tipoChavePix, titularData, conta
        fakeChavePix = new ChavePix(chaveAleatoria,banco, tipoChavePix, conta);
    }

    @Test
    void deve_criar_chave_pix() {
        Mockito.when(databasePortOutPostgreSql.find(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(databasePortOutPostgreSql.save(Mockito.any())).thenReturn(fakeChavePix);
        ChavePix chavePix = chavePixService.createChavePix(criacaoChavePixRequest);
        Mockito.verify(databasePortOutPostgreSql, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(fakeChavePix.getChave(), chavePix.getChave());
    }

}
