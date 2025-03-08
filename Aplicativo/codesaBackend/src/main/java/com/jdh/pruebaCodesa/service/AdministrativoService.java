package com.jdh.pruebaCodesa.service;

import com.jdh.pruebaCodesa.entity.Administrativo;
import com.jdh.pruebaCodesa.entity.Estudiante;
import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.repository.AdministrativoRepository;
import com.jdh.pruebaCodesa.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepository;

    @Autowired
    private PersonaRepository personaRepository;


    public void validarAdministrativo(Administrativo administrativo){
        if(administrativo.getCargo() == null || administrativo.getCargo().trim().isEmpty()){
            throw new RuntimeException("El cargo no debe debe estar vacio");
        }
        if(administrativo.getDepartamento() == null || administrativo.getDepartamento().trim().isEmpty()){
            throw new RuntimeException("El cargo no debe debe estar vacio");
        }

    }

    //Obtener todos los administrativos
    public List<Administrativo> findALLAdministrativos(){
        return administrativoRepository.findAll();
    }

    //Obtener administrativo por id
    public Administrativo findAdministrativoById(Long id_persona){
        return administrativoRepository.findById(id_persona)
                .orElseThrow(() -> new EntityNotFoundException("Administrativo no encontrado con el id "+ id_persona));
    }

    //Validar, asignar persona y guardar administrativo
    public Administrativo saveAdministrativo(Long idPersonas, Administrativo administrativo){

        //Validar si el persona existe
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

        //Asiganar la persona al administrativo
        administrativo.setPersona(persona);

        //Guardar el administrativo
        return administrativoRepository.save(administrativo);
    }

    //Actualizar persona
    @Transactional
    public Administrativo updateAdministrativo(Long id, Administrativo administrativo) {
        validarAdministrativo(administrativo);
        Administrativo administrativoValidar = administrativoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrada con id: " + id));

        administrativoValidar.setCargo(administrativoValidar.getCargo());
        administrativoValidar.setDepartamento(administrativoValidar.getDepartamento());

        return administrativoRepository.save(administrativoValidar);
    }

    //Elimina un administrativo por Id
    public void deleteAdministrativo(Long id) {
        if (!administrativoRepository.existsById(id)) {
            throw new EntityNotFoundException("Administrativo no encontrada con id: " + id);
        }
        administrativoRepository.deleteById(id);
    }
}
