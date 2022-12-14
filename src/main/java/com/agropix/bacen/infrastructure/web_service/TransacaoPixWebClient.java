package com.agropix.bacen.infrastructure.web_service;

import com.agropix.bacen.adapter.out.TransacaoPixPortOut;
import com.agropix.bacen.adapter.out.dto.FalhaTransacao;
import com.agropix.bacen.adapter.out.dto.TransacaoEfetuada;
import com.agropix.bacen.adapter.out.dto.TransacaoPixRequest;
import com.agropix.bacen.domain.type.either.Either;
import com.agropix.bacen.domain.type.either.Left;
import com.agropix.bacen.domain.type.either.Right;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// ToDo: Criar Teste TransacaoPixWebClient

@Component
@RequiredArgsConstructor
public class TransacaoPixWebClient implements TransacaoPixPortOut {

    private static final Logger logger = LoggerFactory.getLogger(TransacaoPixWebClient.class);

    private final RestTemplate restTemplate;
    @Override
    public Either<FalhaTransacao, TransacaoEfetuada> enviarTransacao(String callbackUrl, TransacaoPixRequest request) {
        ResponseEntity<Object> response = restTemplate.postForEntity(callbackUrl, request, Object.class);

        logger.info("Retorno da url de callback {} contem status code {}", callbackUrl, response.getStatusCode());
        if (response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            logger.info("Retornando com status de transacao aprovada");
            return Right.create(new TransacaoEfetuada() {});
        } else {
            logger.info("Retornando com status de transacao recusada");
            return Left.create(new FalhaTransacao() {});
        }
    }
}
