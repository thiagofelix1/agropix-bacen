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
//@Table(name = "conta")
//public class ContaModel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//
//    @Column(name = "numero_conta", nullable = false, unique = true)
//    private String numeroConta;
//
//    @Column(name = "digito", nullable = false)
//    private String digito;
//
//    @OneToOne(optional = false)
//    @JoinColumn(name = "titular", nullable = false, unique = true) // ! Por enquanto o cliente so pode ter 1 conta
//    private PessoaFisicaModel titular;
//
//}
