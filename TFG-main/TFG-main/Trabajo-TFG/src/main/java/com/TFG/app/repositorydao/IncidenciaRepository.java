package com.TFG.app.repositorydao;

import com.TFG.app.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface IncidenciaRepository  extends JpaRepository<Incidencia, Integer> {


    @Query(nativeQuery =true ,value ="SELECT count(*) FROM coche c WHERE c.matricula=:numeroMatricula")
    public int comprobarMatricula (@PathVariable("numeroMatricula") String numeroMatricula);
}

