package com.carol.matricula.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String dni;
    private double edad;
}
