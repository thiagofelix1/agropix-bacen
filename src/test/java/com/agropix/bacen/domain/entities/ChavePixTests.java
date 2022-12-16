package com.agropix.bacen.domain.entities;

import com.agropix.bacen.builders.ContaBuilder;
import com.agropix.bacen.builders.PessoaFisicaBuilder;
import com.agropix.bacen.domain.enums.TipoChavePix;
import com.agropix.bacen.domain.exceptions.ChavePixInvalidaException;
import com.agropix.bacen.builders.BancoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChavePixTests {

    @Test
    public void deveLancarChavePixInvalidaExceptionQuandoCpfForInvalido() {
        Banco banco = BancoBuilder.builder()
            .withNome("ITAU")
            .withCodigo("341")
            .build();

        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder()
            .withCpf("969.477.130.71")
            .withEmail("pessoa.fisica@fmail.com")
            .withTelefone("21986332411")
            .withNome("PESSOA FISICA")
            .build();

        Conta conta = ContaBuilder.builder()
            .withNumeroConta("1234")
            .withDigito("12")
            .withTitular(pessoaFisica)
            .build();

        ChavePixInvalidaException exception = assertThrows(ChavePixInvalidaException.class,
            () -> new ChavePix("96947713071", banco, TipoChavePix.CPF, conta));
        assertEquals("A chave 96947713071 nao e valida para o tipo CPF", exception.getMessage());
    }

    @Test
    public void deveCriarChavePixQuandoCpfForValido() {
        Banco banco = BancoBuilder.builder()
                .withNome("ITAU")
                .withCodigo("341")
                .build();

        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder()
                .withCpf("969.477.130.72")
                .withEmail("pessoa.fisica@fmail.com")
                .withTelefone("21986332412")
                .withNome("PESSOA FISICA")
                .build();

        Conta conta = ContaBuilder.builder()
                .withNumeroConta("1234")
                .withDigito("12")
                .withTitular(pessoaFisica)
                .build();

        ChavePix sut = new ChavePix("96947713072", banco, TipoChavePix.CPF, conta);

        assertDadosChavePix("96947713072", TipoChavePix.CPF, pessoaFisica, conta, sut, banco);
    }

    private void assertDadosChavePix(String valorChaveEsperado, TipoChavePix tipoChaveEsperado, PessoaFisica pessoaFisica, Conta conta, ChavePix chavePix, Banco banco) {
        assertEquals(valorChaveEsperado, chavePix.getChave());
        assertEquals(tipoChaveEsperado, chavePix.getTipo());
        assertEquals(banco.getNome(), chavePix.getBanco().getNome());
        assertEquals(banco.getCodigo(), chavePix.getBanco().getCodigo());
        assertEquals(conta.getNumeroConta(), chavePix.getConta().getNumeroConta());
        assertEquals(conta.getDigito(), chavePix.getConta().getDigito());
        assertEquals(pessoaFisica.getCpf(), chavePix.getConta().getTitular().getCpf());
        assertEquals(pessoaFisica.getTelefone(), chavePix.getConta().getTitular().getTelefone());
        assertEquals(pessoaFisica.getEmail(), chavePix.getConta().getTitular().getEmail());
        assertEquals(pessoaFisica.getNome(), chavePix.getConta().getTitular().getNome());
    }

    @Test
    public void deveLancarChavePixInvalidaExceptionQuandoTipoChaveForEmailEValorChaveFornecidaForEmailInvalido() {
        Banco banco = BancoBuilder.builder()
                .withNome("ITAU")
                .withCodigo("341")
                .build();

        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder()
                .withCpf("969.477.130.72")
                .withEmail("pessoa.fisica@fmail.com")
                .withTelefone("21986332412")
                .withNome("PESSOA FISICA")
                .build();

        Conta conta = ContaBuilder.builder()
                .withNumeroConta("1234")
                .withDigito("12")
                .withTitular(pessoaFisica)
                .build();

        ChavePixInvalidaException exception = assertThrows(ChavePixInvalidaException.class,
            () -> new ChavePix("emailnaovalido@fmail", banco, TipoChavePix.EMAIL, conta));
        assertEquals("A chave emailnaovalido@fmail nao e valida para o tipo EMAIL", exception.getMessage());
    }

    @Test
    public void deveCriarChavePixQuandoTipoChaveForEmailEValorChaveFornecidaForEmailValido() {
        Banco banco = BancoBuilder.builder()
                .withNome("ITAU")
                .withCodigo("341")
                .build();

        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder()
                .withCpf("969.477.130.72")
                .withEmail("pessoa.fisica@fmail.com")
                .withTelefone("21986332412")
                .withNome("PESSOA FISICA")
                .build();

        Conta conta = ContaBuilder.builder()
                .withNumeroConta("1234")
                .withDigito("12")
                .withTitular(pessoaFisica)
                .build();

        ChavePix sut = new ChavePix("email.valido@fmail.com", banco, TipoChavePix.EMAIL, conta);

        assertDadosChavePix("email.valido@fmail.com", TipoChavePix.EMAIL, pessoaFisica, conta, sut, banco);
    }

    @Test
    public void deveLancarChavePixInvalidaExceptionQuandoTipoChaveForCelularEValorChaveFornecidaForCelularInvalido() {
        Banco banco = BancoBuilder.builder()
                .withNome("ITAU")
                .withCodigo("341")
                .build();

        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder()
                .withCpf("969.477.130.72")
                .withEmail("pessoa.fisica@fmail.com")
                .withTelefone("21986332412")
                .withNome("PESSOA FISICA")
                .build();

        Conta conta = ContaBuilder.builder()
                .withNumeroConta("1234")
                .withDigito("12")
                .withTitular(pessoaFisica)
                .build();

        ChavePixInvalidaException exception = assertThrows(ChavePixInvalidaException.class,
                () -> new ChavePix("21882667544", banco, TipoChavePix.CELULAR, conta));
        assertEquals("A chave 21882667544 nao e valida para o tipo CELULAR", exception.getMessage());
    }

    @Test
    public void deveCriarChavePixQuandoTipoChaveForCelularEValorChaveFornecidaForCelularValido() {
        Banco banco = BancoBuilder.builder()
                .withNome("ITAU")
                .withCodigo("341")
                .build();

        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder()
                .withCpf("969.477.130.72")
                .withEmail("pessoa.fisica@fmail.com")
                .withTelefone("21986332412")
                .withNome("PESSOA FISICA")
                .build();

        Conta conta = ContaBuilder.builder()
                .withNumeroConta("1234")
                .withDigito("12")
                .withTitular(pessoaFisica)
                .build();

        ChavePix sut = new ChavePix("11975662322", banco, TipoChavePix.CELULAR, conta);
        assertDadosChavePix("11975662322", TipoChavePix.CELULAR, pessoaFisica, conta, sut, banco);
    }

    @Test
    public void deveLancarChavePixExceptionQuandoTipoChaveForAleatoriaEChaveForUUIDInvalido() {
        Banco banco = BancoBuilder.builder()
                .withNome("ITAU")
                .withCodigo("341")
                .build();

        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder()
                .withCpf("969.477.130.72")
                .withEmail("pessoa.fisica@fmail.com")
                .withTelefone("21986332412")
                .withNome("PESSOA FISICA")
                .build();

        Conta conta = ContaBuilder.builder()
                .withNumeroConta("1234")
                .withDigito("12")
                .withTitular(pessoaFisica)
                .build();

        ChavePixInvalidaException exception = assertThrows(ChavePixInvalidaException.class,
            () -> new ChavePix("639de4d7-c60a-4194-8203-36a68940877ç", banco, TipoChavePix.ALEATORIA, conta));
        assertEquals("A chave 639de4d7-c60a-4194-8203-36a68940877ç nao e valida para o tipo ALEATORIA", exception.getMessage());
    }

    @Test
    public void deveCriarChavePixQuandoTipoChaveForAleatoriaEValorChaveFornecidaForUUIDValido() {
        Banco banco = BancoBuilder.builder()
                .withNome("ITAU")
                .withCodigo("341")
                .build();

        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder()
                .withCpf("969.477.130.72")
                .withEmail("pessoa.fisica@fmail.com")
                .withTelefone("21986332412")
                .withNome("PESSOA FISICA")
                .build();

        Conta conta = ContaBuilder.builder()
                .withNumeroConta("1234")
                .withDigito("12")
                .withTitular(pessoaFisica)
                .build();

        ChavePix sut = new ChavePix("f271b741-86b5-47e4-9c06-0265ce4c6460", banco, TipoChavePix.ALEATORIA, conta);

        assertDadosChavePix("f271b741-86b5-47e4-9c06-0265ce4c6460", TipoChavePix.ALEATORIA, pessoaFisica, conta, sut, banco);
    }
}
