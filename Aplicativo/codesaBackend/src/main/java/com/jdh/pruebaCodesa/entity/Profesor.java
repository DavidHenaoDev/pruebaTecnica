package com.jdh.pruebaCodesa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profesor")
public class Profesor {

    @Id
    private Long id_persona;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "fecha_contratacion")
    private LocalDate fecha_contratacion;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_persona")
    @JsonManagedReference("persona-profesor")
    private Persona persona;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("profesor-curso")
    private List<Curso> cursos;

}
