package com.jdh.pruebaCodesa.controller;

import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.entity.Profesor;
import com.jdh.pruebaCodesa.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/findAllProfesores")
    public ResponseEntity<List<Profesor>> findAllProfesores(){
        List<Profesor> profesores = profesorService.findAllProfesores();
        return profesores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(profesores);
    }

    @GetMapping("/findProfesorById/{personaId}")
    public ResponseEntity<Profesor> findProfesorById(@PathVariable Long personaId) {
        Profesor profesor = profesorService.findProfesorById(personaId);
        return profesor != null ? ResponseEntity.ok(profesor) : ResponseEntity.notFound().build();
    }

    @PostMapping("/saveProfesor/{personaId}")
    public ResponseEntity<?> saveEstudiante(@PathVariable Long personaId, @RequestBody Profesor profesor){
        try {
            //Validar que el objeto profersor no sea nulo
            if (profesor == null){
                return ResponseEntity.badRequest().body("El cuerpo de la solicitud no puede estar vacía");
            }
            //Guardar el profesor
            Profesor nuevoProfesor = profesorService.saveProfesor(personaId, profesor);
            return ResponseEntity.ok(nuevoProfesor);

        } catch (RuntimeException  e) {
            if (e.getMessage().contains("no encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } else if (e.getMessage().contains("ya tiene un profesor asociado")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            } else {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }

    @PutMapping("/updateProfesor/{id}")
    public Profesor updateProfesor(@PathVariable Long id, @Valid @RequestBody Profesor profesor) {
        return profesorService.updateProfesor(id, profesor);
    }

    @DeleteMapping("/deleteProfesor/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }
}
