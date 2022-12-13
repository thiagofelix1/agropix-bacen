package com.agropix.bacen.application.service;

import com.agropix.bacen.domain.entities.ChavePix;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChavePixService {

    // ToDo: Create ChavePixService
    public ChavePix createChavePix(ChavePix chavePix){
        //return chavePixRepository.save(chavePix);
        return chavePix;
    }

    // ToDo: Read ChavePixService
    public ChavePix getChavePix(String chave){
        ChavePix chavePix = new ChavePix();
        //return chavePixRepository.getChavePix(chave);
        return chavePix;
    }

    // ToDo: Update ChavePixService
    public ChavePix updateChavePix(ChavePix chavePix){
        //return chavePixRepository.save(chavePix);
        return chavePix;
    }

    // ToDo: Delete ChavePixService
    public ChavePix deleteChavePix(String chave){
        ChavePix chavePix = new ChavePix();
        //return chavePixRepository.delete(chavePix);
        return chavePix;
    }
}
