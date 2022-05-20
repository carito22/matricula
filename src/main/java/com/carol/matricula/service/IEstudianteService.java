package com.carol.matricula.service;

import java.util.List;

import com.carol.matricula.model.Estudiante;

public interface IEstudianteService extends ICRUD<Estudiante, Integer> {
    List<Estudiante> obtenerListaEstudianteOrdenada();
}
