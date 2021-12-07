package com.TFG.app.repositorydao;


import com.TFG.app.entity.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Integer> {
    Optional<Coche> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
