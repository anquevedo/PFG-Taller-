package com.TFG.app.service;

import com.TFG.app.entity.Administrador;
import com.TFG.app.repositorydao.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
public class AdministradorServiceImpl implements AdministradorService{
    @Autowired
    private AdministradorRepository AdministradorRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Administrador> findAll() {
        return AdministradorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Administrador> findAll(Pageable pageable) {
        return AdministradorRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Administrador> findByDni(Long dni) {
        return AdministradorRepository.findById(dni);
    }

    @Override
    @Transactional
    public Administrador save(Administrador administrador) {
        return AdministradorRepository.save(administrador);
    }

    @Override
    @Transactional
    public void deleteByDni(Long dni) { AdministradorRepository.deleteById(dni); }
}

