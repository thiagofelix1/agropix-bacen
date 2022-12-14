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
//@Table(name = "pessoa_fisica")
//public class PessoaFisicaModel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//
//    @Column(name = "nome", nullable = false)
//    private String nome;
//
//    @Column(name = "cpf", nullable = false, unique = true)
//    private String cpf;
//
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "telefone", nullable = false, unique = true)
//    private String telefone;
//
//}
