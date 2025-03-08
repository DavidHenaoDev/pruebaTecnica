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
@Table(name = "administrativo")
public class Administrativo {

    @Id
    private Long id_persona;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "departamento")
    private String departamento;

    @OneToOne
    @JoinColumn(name = "id_persona")
    @MapsId
    @JsonManagedReference("persona-administrativo")
    private Persona persona;


}
