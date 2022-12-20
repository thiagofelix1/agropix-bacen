package com.agropix.bacen.adapter.in.web.controller;

import com.agropix.bacen.adapter.in.web.dto.request.transacao.TransacaoRequest;
import com.agropix.bacen.adapter.in.web.dto.response.TransacaoResponse;
import com.agropix.bacen.application.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencia-pix")
@RequiredArgsConstructor
public class TransacaoPixController {
    private final TransacaoService service;

    @PostMapping
    public ResponseEntity<TransacaoResponse> efetuarTransacao(@RequestBody TransacaoRequest request) {
        return ResponseEntity.ok(service.executarTransacao(request));
    }
}
