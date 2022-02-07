package com.TFG.app.service;

import com.TFG.app.entity.Incidencia;
import com.TFG.app.repositorydao.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IncidenciaService {

        @Autowired
        IncidenciaRepository incidenciaRepository;

        public List<Incidencia> list(){
                return incidenciaRepository.findAll();
        }

        public Optional<Incidencia> getOne(int id){
                return incidenciaRepository.findById(id);
        }


        public void  save(Incidencia incidencia){
                incidenciaRepository.save(incidencia);
        }

        public void delete(int id){
                incidenciaRepository.deleteById(id);
        }

        public boolean existsById(int id){
                return incidenciaRepository.existsById(id);
        }

}
