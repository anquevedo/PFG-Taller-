package com.TFG.app.repositorydao;


import com.TFG.app.entity.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Integer> {
    Optional<Coche> findByMatricula(String matricula);
    boolean existsByMatricula(String matricula);

    @Query(nativeQuery =true ,value ="SELECT c.matricula FROM coche c WHERE c.id=:id")
    public String buscarMatricula (@PathVariable("id") int id);

    @Modifying
    @Query(nativeQuery =true ,value ="DELETE i FROM incidencia i WHERE i.matricula_coche=:matricula")
    public void borrarMatricula (@PathVariable("matricula") String matricula);

}
