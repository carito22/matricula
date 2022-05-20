package com.carol.matricula.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String nombres;
    @Column(length = 150, nullable = false)
    private String apellidos;
    @Column(unique = true, nullable = false)
    private String dni;
    private double edad;

}