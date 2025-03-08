package com.jdh.pruebaCodesa.controller;

import com.jdh.pruebaCodesa.entity.Curso;
import com.jdh.pruebaCodesa.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "http://localhost:4200")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping("/findAllCursos")
    public ResponseEntity<List<Curso>> findAllCursos() {
        List<Curso> cursos = cursoService.findAllCursos();
        return cursos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cursos);
    }

    @GetMapping("/findCursoById/{id}")
    public ResponseEntity<Curso> findCursoById(@PathVariable Long id) {
        try {
            Curso curso = cursoService.findCursoById(id);
            return ResponseEntity.ok(curso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/saveCurso/{idProfesor}")
    public ResponseEntity<?> saveCurso(@PathVariable Long idProfesor, @RequestBody Curso curso) {
        try {
            if (curso == null) {
                return ResponseEntity.badRequest().body("El cuerpo de la solicitud no puede estar vacío");
            }
            Curso nuevoCurso = cursoService.saveCurso(idProfesor, curso);
            return ResponseEntity.ok(nuevoCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/updateCurso/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        try {
            Curso cursoActualizado = cursoService.updateCurso(id, curso);
            return ResponseEntity.ok(cursoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteCurso/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        try {
            cursoService.deleteCurso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
