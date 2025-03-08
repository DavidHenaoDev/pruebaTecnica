package com.jdh.pruebaCodesa.controller;


import com.jdh.pruebaCodesa.entity.Administrativo;
import com.jdh.pruebaCodesa.service.AdministrativoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrativo")
@CrossOrigin(origins = "http://localhost:4200")
public class AdministrativoController {

    @Autowired
    private AdministrativoService administrativoService;

    @GetMapping("/findAllAdministrativo")
    public ResponseEntity<List<Administrativo>> findAllAdministrativo(){
        List<Administrativo> administrativos= administrativoService.findALLAdministrativos();
        return administrativos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(administrativos);
    }

    @GetMapping("/findAdministrativoById/{personaId}")
    public ResponseEntity<Administrativo> findAdministrativoById(@PathVariable Long personaId) {
        Administrativo administrativo = administrativoService.findAdministrativoById(personaId);
        return administrativo != null ? ResponseEntity.ok(administrativo) : ResponseEntity.notFound().build();
    }

    @PostMapping("/saveAdministrativo/{personaId}")
    public ResponseEntity<?> saveAdministrativo(@PathVariable Long personaId, @RequestBody Administrativo administrativo){
        try {
            //Validar que el objeto administrativo no sea nulo
            if (administrativo == null){
                return ResponseEntity.badRequest().body("El cuerpo de la solicitud no puede estar vacía");
            }
            //Guardar el administrativo
            Administrativo nuevoAdministrativo = administrativoService.saveAdministrativo(personaId, administrativo);
            return ResponseEntity.ok(nuevoAdministrativo);

        } catch (RuntimeException  e) {
            if (e.getMessage().contains("no encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } else if (e.getMessage().contains("ya tiene un administrativo asociado")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            } else {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    }

    @PutMapping("/updateAdministrativo/{id}")
    public Administrativo updateAdministrativo(@PathVariable Long id, @Valid @RequestBody Administrativo administrativo) {
        return administrativoService.updateAdministrativo(id, administrativo);
    }

    @DeleteMapping("/deleteAdministrativo/{id}")
    public ResponseEntity<Void> deleteAdministrativo(@PathVariable Long id) {
        administrativoService.deleteAdministrativo(id);
        return ResponseEntity.noContent().build();
    }



}
