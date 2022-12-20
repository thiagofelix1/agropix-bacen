package com.agropix.bacen.builders;

import com.agropix.bacen.adapter.in.web.dto.request.transacao.TransacaoRequest;

public class TransacaoBuilder {
        public static class RequestBuilder {
            private String chaveOrigem;
            private String chaveDestino;
            private Double valor;

            private RequestBuilder() {  }

            public static RequestBuilder builder() {
                return new RequestBuilder();
            }

            public RequestBuilder withChaveOrigem(String chaveOrigem) {
                this.chaveOrigem = chaveOrigem;

                return this;
            }

            public RequestBuilder withChaveDestino(String chaveDestino) {
                this.chaveDestino = chaveDestino;

                return this;
            }

            public RequestBuilder withValor(double valor) {
                this.valor = valor;

                return this;
            }

            public TransacaoRequest build() {
                var request = new TransacaoRequest();

                request.setChaveDestino(this.chaveDestino);
                request.setChaveDestino(this.chaveOrigem);
                request.setValor(this.valor);

                return request;
            }
        }
}
