package com.agropix.bacen.infrastructure.web_service;

import com.agropix.bacen.adapter.out.TransacaoPixPortOut;
import com.agropix.bacen.adapter.out.dto.FalhaTransacao;
import com.agropix.bacen.adapter.out.dto.TransacaoEfetuada;
import com.agropix.bacen.adapter.out.dto.TransacaoPixRequest;
import com.agropix.bacen.domain.type.either.Either;
import com.agropix.bacen.domain.type.either.Left;
import com.agropix.bacen.domain.type.either.Right;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TransacaoPixWebClient implements TransacaoPixPortOut {
    private final RestTemplate restTemplate;
    @Override
    public Either<FalhaTransacao, TransacaoEfetuada> enviarTransacao(String callbackUrl, TransacaoPixRequest request) {
        ResponseEntity<Object> response = restTemplate.postForEntity(callbackUrl, request, Object.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return Right.create(new TransacaoEfetuada() {});
        } else {
            return Left.create(response::getStatusCodeValue);
        }
    }
}
