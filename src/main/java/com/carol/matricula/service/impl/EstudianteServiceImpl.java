package com.carol.matricula.service.impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.persistence.OrderBy;

import com.carol.matricula.model.Estudiante;
import com.carol.matricula.repo.IEstudianteRepo;
import com.carol.matricula.repo.IGenericRepo;
import com.carol.matricula.service.IEstudianteService;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, Integer> implements IEstudianteService {
    @Autowired
    private IEstudianteRepo repo;

    @Override
    protected IGenericRepo<Estudiante, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Estudiante> obtenerListaEstudianteOrdenada() {

        List<Estudiante> lista = repo.findAll().stream()
                .sorted(Comparator.comparing(Estudiante::getEdad).reversed())
                .collect(Collectors.toList());
        return lista;
    }

}
