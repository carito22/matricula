package com.carol.matricula.service.impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.carol.matricula.model.Matricula;
import com.carol.matricula.model.MatriculaDetalle;
import com.carol.matricula.repo.IGenericRepo;
import com.carol.matricula.repo.IMatriculaRepo;
import com.carol.matricula.service.IMatriculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static java.util.stream.Collectors.*;

@Service
public class MatriculaServiceImpl extends CRUDImpl<Matricula, Integer> implements IMatriculaService {
    @Autowired
    private IMatriculaRepo repo;

    @Override
    protected IGenericRepo<Matricula, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Matricula saveTransactional(Matricula m, List<MatriculaDetalle> details) {
        details.forEach(d -> d.setMatricula(m));
        m.setDetalles(details);
        return repo.save(m);
    }

    @Override
    public Map<String, List<String>> obtenerEstudiantesPorCurso() {
        Stream<List<MatriculaDetalle>> stream = repo.findAll().stream().map(Matricula::getDetalles);
        Stream<MatriculaDetalle> streamDetail = stream.flatMap(Collection::stream);

        Map<String, List<String>> map = streamDetail
                .collect(groupingBy(md -> md.getCurso().getNombre(),
                        Collectors.mapping(m -> m.getMatricula().getEstudiante().getNombres(), Collectors.toList())));

        Map<String, List<String>> res = map.entrySet()
                .stream()
                .collect(
                        toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue));

        return res;
    }

}
