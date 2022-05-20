package com.carol.matricula;

import com.carol.matricula.dto.CursoDTO;
import com.carol.matricula.dto.EstudianteDTO;
import com.carol.matricula.dto.MatriculaDTO;
import com.carol.matricula.dto.MatriculaDetalleDTO;
import com.carol.matricula.model.Curso;
import com.carol.matricula.model.Estudiante;
import com.carol.matricula.model.Matricula;
import com.carol.matricula.model.MatriculaDetalle;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("cursoMapper")
    public ModelMapper cursoMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<CursoDTO, Curso> typeMap = mapper.createTypeMap(CursoDTO.class, Curso.class);
        typeMap.addMapping(CursoDTO::getId, Curso::setId);
        typeMap.addMapping(CursoDTO::getNombre, Curso::setNombre);
        typeMap.addMapping(CursoDTO::getSiglas, Curso::setSiglas);
        typeMap.addMapping(CursoDTO::isEstado, Curso::setEstado);
        return mapper;
    }


    @Bean("estudianteMapper")
    public ModelMapper estudianteMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<EstudianteDTO, Estudiante> typeMap = mapper.createTypeMap(EstudianteDTO.class, Estudiante.class);
        typeMap.addMapping(EstudianteDTO::getId, Estudiante::setId);
        typeMap.addMapping(EstudianteDTO::getNombres, Estudiante::setNombres);
        typeMap.addMapping(EstudianteDTO::getApellidos, Estudiante::setApellidos);
        typeMap.addMapping(EstudianteDTO::getDni, Estudiante::setDni);
        typeMap.addMapping(EstudianteDTO::getEdad, Estudiante::setEdad);
        return mapper;
    }


    @Bean("matriculaMapper")
    public ModelMapper matriculaMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<MatriculaDTO, Matricula> typeMap = mapper.createTypeMap(MatriculaDTO.class, Matricula.class);
        typeMap.addMapping(MatriculaDTO::getId, Matricula::setId);
        typeMap.addMapping(MatriculaDTO::getFechaMatricula, Matricula::setFechaMatricula);
        typeMap.addMapping(MatriculaDTO::getEstudiante, Matricula::setEstudiante);
        typeMap.addMapping(MatriculaDTO::getDetalles, Matricula::setDetalles);
        return mapper;
    }


    @Bean("matriculaDetalleMapper")
    public ModelMapper matriculaDetalleMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<MatriculaDetalleDTO, MatriculaDetalle> typeMap = mapper.createTypeMap(MatriculaDetalleDTO.class, MatriculaDetalle.class);
        typeMap.addMapping(MatriculaDetalleDTO::getAula, MatriculaDetalle::setAula);
        typeMap.addMapping(MatriculaDetalleDTO::getCurso, MatriculaDetalle::setCurso);
        typeMap.addMapping(MatriculaDetalleDTO::getMatricula, MatriculaDetalle::setMatricula);
        return mapper;
    }
}
