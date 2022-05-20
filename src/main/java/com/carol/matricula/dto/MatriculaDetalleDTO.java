package com.carol.matricula.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDetalleDTO {
    private MatriculaDTO matricula;
    private CursoDTO curso;
    private String aula;

}
