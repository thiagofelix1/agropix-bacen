package com.agropix.bacen.application.service;

import com.agropix.bacen.adapter.in.web.dto.request.transacao.TransacaoRequest;
import com.agropix.bacen.adapter.out.TransacaoPixPortOut;
import com.agropix.bacen.application.exceptions.ItemNaoEncontradoException;
import com.agropix.bacen.application.exceptions.PedidoTransacaoInvalidoException;
import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.builders.TransacaoBuilder;
import com.agropix.bacen.domain.entities.ChavePix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransacaoServiceTests {

    @Test
    public void executarTransacaoRetornaItemNaoEncontradoExceptionQuandoChaveOrigemNaoEstiverSalvaNoBacen() {
        final String CHAVE_NAO_EXISTENTE_MOCK = "CHAVE_NAO_EXISTENTE";

        TransacaoRequest request = TransacaoBuilder.RequestBuilder.builder()
            .withChaveOrigem(CHAVE_NAO_EXISTENTE_MOCK)
            .withValor(1_000_000_000)
            .withChaveDestino("CHAVE_DESTINO")
            .build();

        DataBasePortOut databaseMock = mock(DataBasePortOut.class);
        when(databaseMock.find(CHAVE_NAO_EXISTENTE_MOCK)).thenReturn(Optional.empty());
        when(databaseMock.find("CHAVE_DESTINO")).thenReturn(Optional.of(new ChavePix(null, null, null, null)));

        TransacaoPixPortOut transacaoPixPortOutMock = mock(TransacaoPixPortOut.class);


        TransacaoService sut = new TransacaoService(databaseMock, transacaoPixPortOutMock);

        PedidoTransacaoInvalidoException exception = assertThrows(PedidoTransacaoInvalidoException.class, () -> sut.executarTransacao(request));
        assertEquals("Sem chave salva para o valor da chave de origem", "");

    }

}
