package com.jdh.pruebaCodesa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estudiante")
public class Estudiante {

    @Id
    private Long id_persona;

    @Column(name = "numero_matricula")
    private String numero_matricula;

    @Column(name = "grado")
    private String grado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_persona")
    @JsonManagedReference("persona-estudiante")
    private Persona persona;
}
