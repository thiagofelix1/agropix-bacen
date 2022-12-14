package com.agropix.bacen.adapter.in.web.dto.response;

import com.agropix.bacen.domain.entities.ChavePix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ChavePixResponse {
    private final String nomeTitular;
    private final String chave;
    private final String tipo;

    public static ChavePixResponse toResponse(ChavePix entity) {
        return new ChavePixResponse(entity.getConta().getTitular().getNome(), entity.getChave(), entity.getTipo().toString());
    }
}
