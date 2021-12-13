package com.TFG.app.repositorydao;

import com.TFG.app.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncidenciaRepository  extends JpaRepository<Incidencia, Integer> {
    Optional<Incidencia> findByNumeroIncidencia(String numeroIncidencia);
    boolean existsByNumeroIncidencia(String numeroIncidencia);
}
