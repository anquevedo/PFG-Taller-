package com.TFG.app.service;

import com.TFG.app.entity.Coche;
import com.TFG.app.repositorydao.CocheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CocheService {

    @Autowired
    CocheRepository cocheRepository;

    public List<Coche> list(){
        return cocheRepository.findAll();
    }

    public Optional<Coche> getOne(int id){
        return cocheRepository.findById(id);
    }

    public Optional<Coche> getByNombre(String nombre){
        return cocheRepository.findByNombre(nombre);
    }

    public void  save(Coche producto){
        cocheRepository.save(producto);
    }

    public void delete(int id){
        cocheRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return cocheRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return cocheRepository.existsByNombre(nombre);
    }
}
