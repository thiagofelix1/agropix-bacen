package com.agropix.bacen.domain.entities;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Banco {
    private String nome;
    private Long codigo;
}
