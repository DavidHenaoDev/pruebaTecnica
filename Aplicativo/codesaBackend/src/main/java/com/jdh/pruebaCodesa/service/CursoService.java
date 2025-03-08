package com.jdh.pruebaCodesa.service;

import com.jdh.pruebaCodesa.entity.Curso;
import com.jdh.pruebaCodesa.entity.Estudiante;
import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.entity.Profesor;
import com.jdh.pruebaCodesa.repository.CursoRepository;
import com.jdh.pruebaCodesa.repository.ProfesorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    public void validarCurso(Curso curso) {
        if (curso.getNombre() == null || curso.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre no puede estar vacío");
        }
        if (curso.getDescripcion() == null || curso.getDescripcion().trim().isEmpty()) {
            throw new RuntimeException("La descripcion no puede estar vacío");
        }
        if (curso.getCreditos() == null) {
            throw new RuntimeException("Los Creditos no puede estar vacío");
        }

    }

    //mostrar todos los cursos mediante una lista
    public List<Curso> findAllCursos(){
        return cursoRepository.findAll();
    }

    //buscar un estudainte en especifico por su ID
    public Curso findCursoById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrada con el id "+ id));
    }

    //Validar, asignar persona y guardar Curso
    public Curso saveCurso(Long idPersonas, Curso curso){

        //Validar si el profesor existe
        Profesor profesor = profesorRepository.findById(idPersonas)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrada con el ID: "+ idPersonas));

        //Asiganar profesor al Curso
        curso.setProfesor(profesor);

        //Guardar el Curso
        return cursoRepository.save(curso);
    }

    //Actualizar Curso
    @Transactional
    public Curso updateCurso(Long id, Curso curso) {
        validarCurso(curso);
        Curso cursoValidar = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));

        cursoValidar.setNombre(curso.getNombre());
        cursoValidar.setDescripcion(curso.getDescripcion());
        cursoValidar.setCreditos(curso.getCreditos());

        return cursoRepository.save(cursoValidar);
    }

    //Elimina un Curso por Id
    public void deleteCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new EntityNotFoundException("Curso no encontrada con id: " + id);
        }
        cursoRepository.deleteById(id);
    }
}
