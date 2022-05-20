package com.carol.matricula.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(unique = true, length = 20, nullable = false)
    private String siglas;
    private boolean estado;
}
