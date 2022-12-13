package com.agropix.bacen.adapter.in.controller;

import com.agropix.bacen.application.service.ChavePixService;
import com.agropix.bacen.domain.entities.ChavePix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/chavePix")
public class ChavePixController {

    @Autowired
    private ChavePixService chavePixService;

    // ToDo: Create ChaveController
    public ResponseEntity<ChavePix> createChavePix(@RequestBody ChavePix chavePix){
        return ResponseEntity.ok().body(chavePixService.createChavePix(chavePix));
    }

    // ToDo: Read Chave Controller
    public ResponseEntity<ChavePix> getChavePix(@RequestBody String chave){
        return ResponseEntity.ok().body(chavePixService.getChavePix(chave));
    }

    // ToDo: Update Chave Controller
    public ResponseEntity<ChavePix> updateChavePix(@RequestBody ChavePix chavePix){
        return ResponseEntity.ok().body(chavePixService.updateChavePix(chavePix));
    }

    // ToDo: Delete Chave Controller
    public ResponseEntity<ChavePix> deleteChavePix(@RequestBody String chave){
        return ResponseEntity.ok().body(chavePixService.deleteChavePix(chave));
    }
}
