package com.TFG.app.service;


import com.TFG.app.entity.Administrador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdministradorService {
    public Iterable<Administrador> findAll();

    public Page<Administrador> findAll(Pageable pageable);

    public Optional<Administrador> findByDni(Long dni);

    public Administrador save(Administrador administrador);

    public void deleteByDni(Long dni);
}
