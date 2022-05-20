package com.carol.matricula.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.carol.matricula.dto.MatriculaDTO;
import com.carol.matricula.exceptions.ModelNotFoundException;
import com.carol.matricula.model.Matricula;
import com.carol.matricula.service.IMatriculaService;

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
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private IMatriculaService service;

    @Autowired
    @Qualifier("matriculaMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> readAll() throws Exception {
        List<MatriculaDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, MatriculaDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/obtenerEstudiantesPorCurso")
    public ResponseEntity<Map<String, List<String>>> obtenerEstudiantesPorCurso() throws Exception {
        Map<String, List<String>> rpta = service.obtenerEstudiantesPorCurso();
        return new ResponseEntity<>(rpta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Matricula matricula = service.readById(id);
        if (matricula == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        MatriculaDTO dto = mapper.map(matricula, MatriculaDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> create(@Valid @RequestBody MatriculaDTO MatriculaDTO) throws Exception {
        Matricula s = mapper.map(MatriculaDTO, Matricula.class);
        Matricula matricula = service.saveTransactional(s, s.getDetalles());
        MatriculaDTO dto = mapper.map(matricula, MatriculaDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MatriculaDTO> update(@Valid @RequestBody MatriculaDTO MatriculaDTO) throws Exception {
        Matricula matricula = service.readById(MatriculaDTO.getId());
        if (matricula == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + MatriculaDTO.getId());
        }
        Matricula r = service.update(mapper.map(MatriculaDTO, Matricula.class));
        MatriculaDTO dto = mapper.map(r, MatriculaDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Matricula matricula = service.readById(id);
        if (matricula == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
