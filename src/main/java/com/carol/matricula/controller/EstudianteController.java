package com.carol.matricula.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.carol.matricula.dto.EstudianteDTO;
import com.carol.matricula.exceptions.ModelNotFoundException;
import com.carol.matricula.model.Estudiante;
import com.carol.matricula.service.IEstudianteService;

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

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private IEstudianteService service;

    @Autowired
    @Qualifier("estudianteMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> readAll() throws Exception {
        List<EstudianteDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, EstudianteDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/obtenerListaEstudianteOrdenada")
    public ResponseEntity<List<EstudianteDTO>> obtenerListaEstudianteOrdenada() throws Exception {
        List<EstudianteDTO> list = service.obtenerListaEstudianteOrdenada().stream()
                .map(c -> mapper.map(c, EstudianteDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Estudiante cat = service.readById(id);
        if (cat == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        EstudianteDTO dto = mapper.map(cat, EstudianteDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@Valid @RequestBody EstudianteDTO categoryDto) throws Exception {
        Estudiante cat = service.create(mapper.map(categoryDto, Estudiante.class));
        EstudianteDTO dto = mapper.map(cat, EstudianteDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EstudianteDTO> update(@Valid @RequestBody EstudianteDTO categoryDto) throws Exception {
        Estudiante cat = service.readById(categoryDto.getId());
        if (cat == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + categoryDto.getId());
        }
        Estudiante Estudiante = service.update(mapper.map(categoryDto, Estudiante.class));
        EstudianteDTO dto = mapper.map(Estudiante, EstudianteDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Estudiante cat = service.readById(id);
        if (cat == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
