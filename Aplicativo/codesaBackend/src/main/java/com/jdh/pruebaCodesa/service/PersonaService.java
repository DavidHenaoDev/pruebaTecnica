package com.jdh.pruebaCodesa.service;

import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    //Validar persona
    public void validarPersona(Persona persona) {
        if (persona.getNombre() == null || persona.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre no puede estar vacío");
        }
        if (persona.getApellido() == null || persona.getApellido().trim().isEmpty()) {
            throw new RuntimeException("El apellido no puede estar vacío");
        }
        if (persona.getFecha_nacimiento() == null || persona.getFecha_nacimiento().isAfter(LocalDate.now())) {
            throw new RuntimeException("El fecha de nacimiendo  debe ser valida y no puede ser una fecha futura");
        }
        if (persona.getEmail() == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", persona.getEmail())) {
            throw new RuntimeException("El Email no puede estar vacío y tiene que ser valido");
        }
        if (persona.getTelefono() == null || !Pattern.matches("\\d{10}", persona.getTelefono())) {
            throw new RuntimeException("El Telefono no puede estar vacío y debe tener exactamente 10 digitos");
        }
    }

    public List<Persona> findALLPersonas(){
        return personaRepository.findAll();
    }

    public Persona findPersonaById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con el id "+ id));
    }

    public Persona savePersona(Persona persona){
        return personaRepository.save(persona);
    }

    //Actualizar persona
    @Transactional
    public Persona updatePersona(Long id, Persona persona) {
        validarPersona(persona);
        Persona personaValidar = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));

        personaValidar.setNombre(persona.getNombre());
        personaValidar.setApellido(persona.getApellido());
        personaValidar.setFecha_nacimiento(persona.getFecha_nacimiento());
        personaValidar.setEmail(persona.getEmail());
        personaValidar.setTelefono(persona.getTelefono());

        return personaRepository.save(personaValidar);
    }

    //Elimina un persona por Id
    public void deletePersona(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new EntityNotFoundException("Persona no encontrada con id: " + id);
        }
        personaRepository.deleteById(id);
    }


}
