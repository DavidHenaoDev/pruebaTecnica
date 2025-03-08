package com.jdh.pruebaCodesa.repository;

import com.jdh.pruebaCodesa.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
