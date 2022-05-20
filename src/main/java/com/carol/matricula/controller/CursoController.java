package com.carol.matricula.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.carol.matricula.dto.CursoDTO;
import com.carol.matricula.exceptions.ModelNotFoundException;
import com.carol.matricula.model.Curso;
import com.carol.matricula.service.ICursoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private ICursoService service;

    @Autowired
    @Qualifier("cursoMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> readAll() throws Exception {
        List<CursoDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, CursoDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Curso cat = service.readById(id);
        if (cat == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        CursoDTO dto = mapper.map(cat, CursoDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoDTO categoryDto) throws Exception {
        Curso cat = service.create(mapper.map(categoryDto, Curso.class));
        CursoDTO dto = mapper.map(cat, CursoDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CursoDTO> update(@Valid @RequestBody CursoDTO categoryDto) throws Exception {
        Curso cat = service.readById(categoryDto.getId());
        if (cat == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + categoryDto.getId());
        }
        Curso Curso = service.update(mapper.map(categoryDto, Curso.class));
        CursoDTO dto = mapper.map(Curso, CursoDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Curso cat = service.readById(id);
        if (cat == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
