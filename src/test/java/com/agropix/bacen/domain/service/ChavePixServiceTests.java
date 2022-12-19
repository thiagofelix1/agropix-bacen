package com.agropix.bacen.domain.service;

import com.agropix.bacen.adapter.in.web.dto.request.criacao_chave.BancoData;
import com.agropix.bacen.adapter.in.web.dto.request.criacao_chave.CriacaoChavePixRequest;
import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.application.service.ChavePixService;
import com.agropix.bacen.builders.BancoBuilder;
import com.agropix.bacen.domain.entities.Banco;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.enums.TipoChavePix;
import com.agropix.bacen.domain.exceptions.ChavePixExistenteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ChavePixServiceTests {

    @Nested
    class CreateChavePix {

        @Test
        public void deveLancarChavePixExistenteExceptionQuandoChaveEstiverSalvaNoRepositorio() {

            final String CHAVE_MOCK = "CHAVE_JA_EXISTENTE_MOCK";

            DataBasePortOut dataBasePortOutMock = mock(DataBasePortOut.class);
            when(dataBasePortOutMock.find(CHAVE_MOCK)).thenReturn(Optional.of(mock(ChavePix.class)));
            CriacaoChavePixRequest request = new CriacaoChavePixRequest();
            request.setChave(CHAVE_MOCK);


            ChavePixService sut = new ChavePixService(dataBasePortOutMock);


            ChavePixExistenteException exception = assertThrows(ChavePixExistenteException.class, () -> sut.createChavePix(request));
            assertEquals("A chave CHAVE_JA_EXISTENTE_MOCK já existe.", exception.getMessage());
        }

        @Test
        public void quandoChaveNaoForExistenteDeveChamarRepositorioParaSalvar() {

            final String CHAVE_MOCK = "CHAVE_JA_EXISTENTE_MOCK";

            DataBasePortOut dataBasePortOutMock = mock(DataBasePortOut.class);
            when(dataBasePortOutMock.find(CHAVE_MOCK)).thenReturn(Optional.empty());
            CriacaoChavePixRequest request = setupRequestParaCriacaoChave(new CriacaoChavePixRequest());


            ChavePixService sut = new ChavePixService(dataBasePortOutMock);


            ChavePixExistenteException exception = assertThrows(ChavePixExistenteException.class, () -> sut.createChavePix(request));
            assertEquals("A chave CHAVE_JA_EXISTENTE_MOCK já existe.", exception.getMessage());
        }

        private CriacaoChavePixRequest setupRequestParaCriacaoChave(CriacaoChavePixRequest request) {

            BancoData banco = new BancoData();

            request.setChave("3744b114-fda1-4aee-92c5-41e4a0504fc6");
            request.setTipo(TipoChavePix.ALEATORIA);
            request.setBanco(banco);

            return request;
        }
    }
}
