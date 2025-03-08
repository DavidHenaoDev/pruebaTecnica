package com.jdh.pruebaCodesa.controller;


import com.jdh.pruebaCodesa.entity.Estudiante;
import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.entity.Profesor;
import com.jdh.pruebaCodesa.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;


    @GetMapping("/findAllEstudiantes")
    public ResponseEntity<List<Estudiante>> findAllEstudiantes(){
        List<Estudiante> estudiantes = estudianteService.findAllEstudiantes();
        return estudiantes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/findEstudianteById/{personaId}")
    public ResponseEntity<Estudiante> findEstudianteById(@PathVariable Long personaId) {
        Estudiante estudiante = estudianteService.findEstudianteById(personaId);
        return estudiante != null ? ResponseEntity.ok(estudiante) : ResponseEntity.notFound().build();
    }

    @PostMapping("/saveEstudiante/{personaId}")
    public ResponseEntity<?> saveEstudiante(@PathVariable Long personaId, @RequestBody Estudiante estudiante){
        try {
            //Validar que el objeto estudainte no sea nulo
            if (estudiante == null){
                return ResponseEntity.badRequest().body("El cuerpo de la solicitud no puede estar vacía");
            }
            //Guardar el estudiante
            Estudiante nuevoEstudiante = estudianteService.saveEstudiante(personaId, estudiante);
            return ResponseEntity.ok(nuevoEstudiante);

        } catch (RuntimeException  e) {
            if (e.getMessage().contains("no encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } else if (e.getMessage().contains("ya tiene un estudiante asociado")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            } else {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    }

    @PutMapping("/updateEstudiante/{id}")
    public Estudiante updateEstudiante(@PathVariable Long id, @Valid @RequestBody Estudiante estudiante) {
        return estudianteService.updateEstudiante(id, estudiante);
    }

    @DeleteMapping("/deleteEstudiante/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return ResponseEntity.noContent().build();
    }

}
