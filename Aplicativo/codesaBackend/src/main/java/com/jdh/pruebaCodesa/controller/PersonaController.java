package com.jdh.pruebaCodesa.controller;

import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "http://localhost:4200")	
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/findAllPersonas")
    public ResponseEntity<List<Persona>> findAllPersonas(){
        List<Persona> personas = personaService.findALLPersonas();
        return personas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(personas);
    }

    @GetMapping("/findPersonaById/{idPersona}")
    public ResponseEntity<Persona> findPersonaById(@PathVariable Long idPersona) {
        Persona persona = personaService.findPersonaById(idPersona);
        return persona != null ? ResponseEntity.ok(persona) : ResponseEntity.notFound().build();
    }

    @PostMapping("/savePersonas")
    public ResponseEntity<Persona> savePersona(@Valid @RequestBody Persona persona){
        try {
            Persona nuevoPersona = personaService.savePersona(persona);
            return ResponseEntity.ok(nuevoPersona);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/updatePersona/{id}")
    public Persona updatePersona(@PathVariable Long id, @Valid @RequestBody Persona persona) {
        return personaService.updatePersona(id, persona);
    }

    @DeleteMapping("/deletePersonas/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }
}
