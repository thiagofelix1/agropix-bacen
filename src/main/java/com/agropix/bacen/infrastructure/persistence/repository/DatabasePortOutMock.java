package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.domain.entities.ChavePix;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DatabasePortOutMock implements DataBasePortOut {
    private static final ConcurrentHashMap<String, ChavePix> chaves = new ConcurrentHashMap<>();
    @Override
    public ChavePix save(ChavePix chave) {
        chaves.put(chave.getChave(), chave);

        return chave;
    }

    @Override
    public Optional<ChavePix> find(String chave) {
        return Optional.ofNullable(chaves.get(chave));
    }
}
