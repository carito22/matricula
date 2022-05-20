package com.carol.matricula.service;

import java.util.List;
import java.util.Map;

import com.carol.matricula.model.Matricula;
import com.carol.matricula.model.MatriculaDetalle;

public interface IMatriculaService extends ICRUD<Matricula, Integer> {
    Matricula saveTransactional(Matricula m, List<MatriculaDetalle> details);
    public Map<String, List<String>> obtenerEstudiantesPorCurso();
}
