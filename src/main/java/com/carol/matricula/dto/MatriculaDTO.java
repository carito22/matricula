package com.carol.matricula.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTO {
    private Integer id;
    private LocalDateTime fechaMatricula;
    private EstudianteDTO estudiante;
    private List<MatriculaDetalleDTO> detalles;
}
