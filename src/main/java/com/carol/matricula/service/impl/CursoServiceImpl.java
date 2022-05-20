package com.carol.matricula.service.impl;

import com.carol.matricula.model.Curso;
import com.carol.matricula.repo.ICursoRepo;
import com.carol.matricula.repo.IGenericRepo;
import com.carol.matricula.service.ICursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl extends CRUDImpl<Curso, Integer> implements ICursoService {
    @Autowired
    private ICursoRepo repo;

    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }

}
