package com.agropix.bacen.adapter.in.web.controller;

import com.agropix.bacen.adapter.in.web.dto.request.criacao_chave.CriacaoChavePixRequest;
import com.agropix.bacen.adapter.in.web.dto.response.ChavePixResponse;
import com.agropix.bacen.application.service.ChavePixService;
import com.agropix.bacen.domain.entities.ChavePix;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/chavePix")
@RequiredArgsConstructor
public class ChavePixController {

    private final ChavePixService chavePixService;

    // ToDo: Create ChaveController
    @PostMapping
    public ResponseEntity<ChavePixResponse> createChavePix(@RequestBody CriacaoChavePixRequest request, UriComponentsBuilder uriBuilder){
        ChavePix chavePix = chavePixService.createChavePix(request);

        URI uri = uriBuilder.path("/{chave}").buildAndExpand(chavePix.getChave()).toUri();

        return ResponseEntity.created(uri).body(ChavePixResponse.toResponse(chavePix));
    }

    // ToDo: Read Chave Controller
    @GetMapping("/{chavePix}")
    public ResponseEntity<ChavePixResponse> getChavePix(@PathVariable String chavePix) {
        ChavePix chave = chavePixService.getChavePix(chavePix);
        return ResponseEntity.ok().body(new ChavePixResponse(chave.getConta().getTitular().getNome(), chave.getChave(), chave.getTipo().toString()));
    }

      // ToDo: Update Chave Controller
//    public ResponseEntity<ChavePix> updateChavePix(@RequestBody ChavePix chavePix){
//        return ResponseEntity.ok().body(chavePixService.updateChavePix(chavePix));
//    }
//
      // ToDo: Delete Chave Controller
//    @DeleteMapping("/{chavePix}")
//    public ResponseEntity<ChavePix> deleteChavePix(@PathVariable String chavePix) {
//        return ResponseEntity.ok().body(chavePixService.deleteChavePix(chavePix));
//    }
}
