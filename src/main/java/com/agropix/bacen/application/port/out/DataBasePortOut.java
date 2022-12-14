package com.agropix.bacen.application.port.out;

import com.agropix.bacen.domain.entities.ChavePix;

import java.util.Optional;

public interface DataBasePortOut {

    // ToDo: Create ChavePixPortOut
    ChavePix save(ChavePix chave);
    // ToDo: Read ChavePixPortOut
    Optional<ChavePix> find(String chave);
    // ToDo: Update ChavePixPortOut

    // ToDo: Delete ChavePixPortOut

}
