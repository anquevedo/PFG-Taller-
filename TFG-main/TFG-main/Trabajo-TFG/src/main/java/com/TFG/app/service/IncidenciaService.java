package com.TFG.app.service;

import com.TFG.app.entity.Incidencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IncidenciaService {
    public Iterable<Incidencia> findAll();

    public Page<Incidencia> findAll(Pageable pageable);

    public Optional<Incidencia> findById(Long id);

    public Incidencia save(Incidencia incidencia);

    public void deleteById(Long id);


}