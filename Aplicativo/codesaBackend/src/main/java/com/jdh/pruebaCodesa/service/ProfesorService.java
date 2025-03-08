package com.jdh.pruebaCodesa.service;

import com.jdh.pruebaCodesa.entity.Estudiante;
import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.entity.Profesor;
import com.jdh.pruebaCodesa.repository.PersonaRepository;
import com.jdh.pruebaCodesa.repository.ProfesorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private PersonaRepository personaRepository;

    public void validarProfesor(Profesor profesor) {
        if (profesor.getEspecialidad() == null || profesor.getEspecialidad().trim().isEmpty()) {
            throw new RuntimeException("La especialidad no puede estar vacío");
        }
        if (profesor.getFecha_contratacion() == null) {
            throw new RuntimeException("La fecha de contratacion no puede estar vacío");
        }
    }

    //mostrar todos lo Profesores mediante una lista
    public List<Profesor> findAllProfesores(){
        return profesorRepository.findAll();
    }

    //buscar un Profesor en especifico por su ID
    public Profesor findProfesorById(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrada con el id "+ id));
    }

    //Validar, asignar persona y guardar profesor
    public Profesor saveProfesor(Long idPersonas, Profesor profesor){

        //Validar si la persona existe
        Persona persona = personaRepository.findById(idPersonas)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con el ID: "+ idPersonas));

        //Validar si estudainte tiene ya una persona asociada
        if(persona.getEstudiante() != null){
            throw new RuntimeException("La persona ya tiene un profesor asociado");
        }

        //Validar si administrativo tiene ya una personas asociada
        if(persona.getAdministrativo() != null){
            throw new RuntimeException("La persona ya tiene un administrativo asociado");
        }

        if(persona.getProfesor() != null) {
            throw new RuntimeException("La persona ya tiene un profesor asociado");
        }

        //Asiganar la persona al profesor
        profesor.setPersona(persona);

        //Guardar el profesor
        return profesorRepository.save(profesor);
    }

    //Actualizar Profesor
    @Transactional
    public Profesor updateProfesor(Long id, Profesor profesor) {
        validarProfesor(profesor);
        Profesor profesorValidar = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrada con id: " + id));

        profesorValidar.setEspecialidad(profesor.getEspecialidad());
        profesorValidar.setEspecialidad(profesor.getEspecialidad());

        return profesorRepository.save(profesorValidar);
    }

    //Elimina un Profesor por Id
    public void deleteProfesor(Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new EntityNotFoundException("Profesor no encontrada con id: " + id);
        }
        profesorRepository.deleteById(id);
    }

}
