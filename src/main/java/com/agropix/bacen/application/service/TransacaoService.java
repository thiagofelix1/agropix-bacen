package com.agropix.bacen.application.service;

import com.agropix.bacen.adapter.in.web.dto.request.transacao.TransacaoRequest;
import com.agropix.bacen.adapter.in.web.dto.response.TransacaoResponse;
import com.agropix.bacen.adapter.out.TransacaoPixPortOut;
import com.agropix.bacen.application.dto.TransacaoPixWebRequest;
import com.agropix.bacen.application.exceptions.FalhaTransacaoException;
import com.agropix.bacen.application.exceptions.ItemNaoEncontradoException;
import com.agropix.bacen.application.exceptions.PedidoTransacaoInvalidoException;
import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.TransacaoPix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TransacaoService {

    private static final Logger logger = LoggerFactory.getLogger(TransacaoService.class);

    private final DataBasePortOut repository;
    private final TransacaoPixPortOut transacaoHttpClient;

    public TransacaoService(DataBasePortOut repository, TransacaoPixPortOut transacaoHttpClient) {
        this.repository = repository;
        this.transacaoHttpClient = transacaoHttpClient;
    }

    public TransacaoResponse executarTransacao(TransacaoRequest request) {

        validaRequest(request);
        ChavePix chaveOrigem = repository.find(request.getChaveOrigem())
            .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Sem chave pix encontrada para a origem %s", request.getChaveOrigem())));
        ChavePix chaveDestino = repository.find(request.getChaveDestino())
            .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Sem chave pix encontrada para o destino %s", request.getChaveDestino())));

        TransacaoPix transacao = request.toEntity(chaveOrigem, chaveDestino);
        String urlWebHookDestibo = repository.getUrlNotificacao(chaveDestino.getBanco().getNome());

        var responseBancoDestino =transacaoHttpClient.enviarTransacao(urlWebHookDestibo,
            new TransacaoPixWebRequest(request.getChaveOrigem(), request.getChaveDestino(), chaveOrigem.getBanco().getNome(), transacao.getValor().toString())
        );

        if (responseBancoDestino.isLeft()) {
            logger.info("Transacao entre chave origem {} e chave destino {} não pode ser efetuada", transacao.getChaveOrigem(), transacao.getChaveDestino());
            throw new FalhaTransacaoException();
        }

        return new TransacaoResponse(
            transacao.getChaveOrigem().getChave(),
            transacao.getChaveDestino().getChave(),
            transacao.getValor().setScale(2, RoundingMode.CEILING).toPlainString(),
            chaveDestino.getBanco().getNome());
    }

    private void validaRequest(TransacaoRequest request) {

        ArrayList<String> erros = new ArrayList<>();

        Optional<ChavePix> possivelChaveOrigem = repository.find(request.getChaveOrigem());

        if (possivelChaveOrigem.isEmpty()) {
            erros.add(String.format("Sem chave salva para o valor da chave de origem %s", request.getChaveOrigem()));
        }

        Optional<ChavePix> possivelChaveDestino = repository.find(request.getChaveDestino());

        if (possivelChaveDestino.isEmpty()) {
            erros.add(String.format("Sem chave salva para o valor da chave de destino %s", request.getChaveOrigem()));
        }

        if (request.getValor() <= 0) {
            erros.add(String.format("O valor informado na transacao (%s) nao e um valor valido", request.getValor()));
        }

        if (!erros.isEmpty()) {
            throw new PedidoTransacaoInvalidoException(erros);
        }
    }
}
