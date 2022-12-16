package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.domain.entities.Banco;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.Conta;
import com.agropix.bacen.domain.entities.PessoaFisica;
import com.agropix.bacen.domain.enums.TipoChavePix;
import com.agropix.bacen.infrastructure.persistence.model.PessoaFisicaPersistenceModel;
import com.agropix.bacen.infrastructure.persistence.model.TipoChavePixPersistenceModel;
import org.junit.jupiter.api.Nested;
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
class DatabasePortOutPostgreSqlTests {

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

    @Nested
    class SaveTests {
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
        void deveBuscarContaSeExistirPorNumeroConta() {
            when(tipoChavePixRepository.findByTipo(anyString())).thenReturn(Optional.of(new TipoChavePixPersistenceModel()));
            when(contaRepository.existsByNumeroConta(any())).thenReturn(true);

            assertDoesNotThrow(() -> databasePortOutPostgreSql.save(mockChavePix()));

            verify(contaRepository, times(1)).findByNumeroConta(any());
        }

        @Test
        void deveSalvarContaSeNaoExistirPorNumeroConta() {
            PessoaFisica pessoaFisicaEsperada = mockPessoaFisica();
            PessoaFisicaPersistenceModel pessoaFisicaPersistenceModel = PessoaFisicaPersistenceModel.fromEntity(pessoaFisicaEsperada);

            when(tipoChavePixRepository.findByTipo(anyString())).thenReturn(Optional.of(new TipoChavePixPersistenceModel()));
            when(pessoaFisicaRepository.existsByCpf(any())).thenReturn(true);
            when(pessoaFisicaRepository.findByCpf(any())).thenReturn(pessoaFisicaPersistenceModel);
            when(contaRepository.existsByNumeroConta(any())).thenReturn(false);

            ChavePix chavePix = assertDoesNotThrow(() -> databasePortOutPostgreSql.save(mockChavePix()));

            PessoaFisica pessoaFisicaRetornada = chavePix.getConta().getTitular();

            assertEquals(pessoaFisicaEsperada.getNome(), pessoaFisicaRetornada.getNome());
            assertEquals(pessoaFisicaEsperada.getCpf(), pessoaFisicaRetornada.getCpf());
            assertEquals(pessoaFisicaEsperada.getTelefone(), pessoaFisicaRetornada.getTelefone());
            assertEquals(pessoaFisicaEsperada.getEmail(), pessoaFisicaRetornada.getEmail());
            verify(contaRepository, times(1)).saveAndFlush(any());
        }

        @Test
        void deveBuscarBancoSeExistirPorCodigo() {
            when(tipoChavePixRepository.findByTipo(anyString())).thenReturn(Optional.of(new TipoChavePixPersistenceModel()));
            when(bancoRepository.existsByCodigo(any())).thenReturn(true);

            assertDoesNotThrow(() -> databasePortOutPostgreSql.save(mockChavePix()));

            verify(bancoRepository, times(1)).getByCodigo(any());
        }

        @Test
        void deveSalvarBancoSeNaoExistirPorCodigo() {
            when(tipoChavePixRepository.findByTipo(anyString())).thenReturn(Optional.of(new TipoChavePixPersistenceModel()));
            when(bancoRepository.existsByCodigo(any())).thenReturn(false);

            assertDoesNotThrow(() -> databasePortOutPostgreSql.save(mockChavePix()));

            verify(bancoRepository, times(1)).saveAndFlush(any());
        }
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
