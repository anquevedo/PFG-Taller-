package com.TFG.app.repositorydao;

import com.TFG.app.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepository  extends JpaRepository<Incidencia, Long> {
}
