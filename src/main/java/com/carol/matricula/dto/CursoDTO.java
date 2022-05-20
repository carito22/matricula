package com.carol.matricula.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    private Integer id;
    private String nombre;
    private String siglas;
    private boolean estado;
}
