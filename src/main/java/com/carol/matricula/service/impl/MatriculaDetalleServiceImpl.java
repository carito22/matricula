package com.carol.matricula.service.impl;

import com.carol.matricula.model.MatriculaDetalle;
import com.carol.matricula.repo.IGenericRepo;
import com.carol.matricula.repo.IMatriculaDetalleRepo;
import com.carol.matricula.service.IMatriculaDetalleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaDetalleServiceImpl extends CRUDImpl<MatriculaDetalle, Integer>
        implements IMatriculaDetalleService {
    @Autowired
    private IMatriculaDetalleRepo repo;

    @Override
    protected IGenericRepo<MatriculaDetalle, Integer> getRepo() {
        return repo;
    }
}
