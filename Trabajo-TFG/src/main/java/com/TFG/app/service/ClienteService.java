package com.TFG.app.service;

import com.TFG.app.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteService {
    public Iterable<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Optional<Cliente> findByDni(Long dni);

    public Cliente save(Cliente cliente);

    public void deleteByDni(Long dni);
}
