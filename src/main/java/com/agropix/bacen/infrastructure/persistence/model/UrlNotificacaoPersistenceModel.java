package com.agropix.bacen.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "url_notificacao")
public class UrlNotificacaoPersistenceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String url;
    @Column(name = "nome_banco")
    private String nomeBanco;

}
