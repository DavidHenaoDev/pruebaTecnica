package com.jdh.pruebaCodesa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inscripcion")
public class Inscripcion {

    @Id
    @Column(name = "id_inscripcion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_inscripcion;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    @JsonBackReference("persona-inscripcion")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    @JsonBackReference("curso-inscripcion")
    private Curso curso;

    @Column(name = "fecha_inscripcion")
    private LocalDate fecha_inscripcion;

}
