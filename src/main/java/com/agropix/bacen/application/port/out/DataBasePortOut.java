package com.agropix.bacen.application.port.out;

import com.agropix.bacen.domain.entities.ChavePix;

import java.util.Optional;

public interface DataBasePortOut {

    // ToDo: Create ChavePixDatabaseAdapter
    ChavePix save(ChavePix chave);
    // ToDo: Read ChavePixDatabaseAdapter
    Optional<ChavePix> find(String chave);
    // ToDo: Update ChavePixDatabaseAdapter

    // ToDo: Delete ChavePixDatabaseAdapter

}
