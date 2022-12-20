package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.adapter.out.DatabasePortOutPostgreSql;
import com.agropix.bacen.domain.entities.Banco;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.Conta;
import com.agropix.bacen.domain.entities.PessoaFisica;
import com.agropix.bacen.domain.enums.TipoChavePix;
import com.agropix.bacen.infrastructure.persistence.model.TipoChavePixPersistenceModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DatabasePortOutPostgreSqlTests {

    @InjectMocks
    private DatabasePortOutPostgreSql databasePortOutPostgreSql;

    @Mock
    private ChavePixRepository chavePixRepository;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Mock
    private TipoChavePixRepository tipoChavePixRepository;

    @Mock
    private BancoRepository bancoRepository;

    @Test
    void deveLancarExcecaoAoNaoEncontrarTipoChave() {
        when(tipoChavePixRepository.findByTipo(anyString())).thenReturn(Optional.empty());

        ChavePix chavePix = mockChavePix();

        assertThrows(RuntimeException.class, () -> databasePortOutPostgreSql.save(chavePix));
    }

    @Test
    void deveBuscarPessoaFisicaSeExistirPorCpf() {
        when(tipoChavePixRepository.findByTipo(anyString())).thenReturn(Optional.of(new TipoChavePixPersistenceModel()));
        when(pessoaFisicaRepository.existsByCpf(any())).thenReturn(true);

        assertDoesNotThrow(() -> databasePortOutPostgreSql.save(mockChavePix()));

        verify(pessoaFisicaRepository, times(1)).findByCpf(any());
    }

    @Test
    void deveSalvarPessoaFisicaSeNaoExistirPorCpf() {
        when(tipoChavePixRepository.findByTipo(anyString())).thenReturn(Optional.of(new TipoChavePixPersistenceModel()));
        when(pessoaFisicaRepository.existsByCpf(any())).thenReturn(false);

        assertDoesNotThrow(() -> databasePortOutPostgreSql.save(mockChavePix()));

        verify(pessoaFisicaRepository, times(1)).saveAndFlush(any());
    }

    @Test
    void deveBuscarContaPorNumeroSeExistir() {

    }

    private Banco mockBanco() {
        return new Banco("nome", "codigo");
    }

    private TipoChavePix mockTipoChavePix() {
        return TipoChavePix.ALEATORIA;
    }

    private PessoaFisica mockPessoaFisica() {
        return new PessoaFisica("nome", "cpf", "email", "telefone");
    }

    private Conta mockConta() {
        return new Conta("1", "2", mockPessoaFisica());
    }

    private ChavePix mockChavePix() {
        String chave = UUID.randomUUID().toString();
        Banco banco = mockBanco();
        TipoChavePix tipoChavePix = mockTipoChavePix();
        Conta conta = mockConta();
        return new ChavePix(chave, banco, tipoChavePix, conta);
    }

}
