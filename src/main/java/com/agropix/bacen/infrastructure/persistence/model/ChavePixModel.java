//package com.agropix.bacen.infrastructure.persistence.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.UUID;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "chave_pix")
//public class ChavePixModel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "tipo_chave", nullable = false)
//    private TipoChavePixModel tipoChave;
//
//    @Column(name = "chave", unique = true, nullable = false)
//    private String chave;
//
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "banco", nullable = false)
//    private BancoModel banco;
//
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "conta", nullable = false)
//    private ContaModel conta;
//
//}
