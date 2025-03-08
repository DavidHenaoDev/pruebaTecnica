package com.jdh.pruebaCodesa.service;

import com.jdh.pruebaCodesa.entity.Estudiante;
import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.repository.EstudianteRepository;
import com.jdh.pruebaCodesa.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    PersonaRepository personaRepository;

    public void validarEstudiante(Estudiante estudiante) {
        if (estudiante.getNumero_matricula() == null || estudiante.getNumero_matricula().trim().isEmpty()) {
            throw new RuntimeException("El numero de matricula no puede estar vacío");
        }
        if (estudiante.getGrado() == null || estudiante.getGrado().trim().isEmpty()) {
            throw new RuntimeException("El grado no puede estar vacío");
        }
    }

    //mostrar todos lo estudiantes mediante una lista
    public List<Estudiante> findAllEstudiantes(){
        return estudianteRepository.findAll();
    }

    //buscar un estudainte en especifico por su ID
    public Estudiante findEstudianteById(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrada con el id "+ id));
    }

    //Validar, asignar persona y guardar estudiante
    public Estudiante saveEstudiante(Long idPersonas, Estudiante estudiante){

        //Validar si la persona existe
        Persona persona = personaRepository.findById(idPersonas)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrada con el ID: "+ idPersonas));

        //Validar si estudainte tiene ya una persona asociada
        if(persona.getEstudiante() != null){
            throw new RuntimeException("El estudiante ya tiene un estudiante asociado");
        }

        //Validar si administrativo tiene ya una personas asociada
        if(persona.getAdministrativo() != null){
            throw new RuntimeException("La persona ya tiene un administrativo asociado");
        }

        //Asiganar la persona al estudiante
        estudiante.setPersona(persona);

        //Guardar el estudiante
        return estudianteRepository.save(estudiante);
    }

    //Actualizar persona
    @Transactional
    public Estudiante updateEstudiante(Long id, Estudiante estudiante) {
        validarEstudiante(estudiante);
        Estudiante estudianteValidar = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrada con id: " + id));

        estudianteValidar.setNumero_matricula(estudiante.getNumero_matricula());
        estudianteValidar.setGrado(estudiante.getGrado());

        return estudianteRepository.save(estudianteValidar);
    }

    //Elimina un persona por Id
    public void deleteEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new EntityNotFoundException("Estudiante no encontrada con id: " + id);
        }
        estudianteRepository.deleteById(id);
    }



}
