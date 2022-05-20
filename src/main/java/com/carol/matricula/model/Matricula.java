package com.carol.matricula.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime fechaMatricula;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @Column(nullable = false)
    private boolean estado;
    
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<MatriculaDetalle> detalles;

}
