package com.TFG.app.service;

import com.TFG.app.entity.Incidencia;
import com.TFG.app.repositorydao.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

        @Autowired
        private IncidenciaRepository incidenciaRepository;

        @Override
        @Transactional(readOnly = true)
        public Iterable<Incidencia> findAll() {
            return incidenciaRepository.findAll();
        }

        @Override
        @Transactional(readOnly = true)
        public Page<Incidencia> findAll(Pageable pageable) {
            return incidenciaRepository.findAll(pageable);
        }

        @Override
        @Transactional(readOnly = true)
        public Optional<Incidencia> findById(Long id) {
            return incidenciaRepository.findById(id);
        }


        @Override
        @Transactional
        public Incidencia save(Incidencia incidencia) {
            return incidenciaRepository.save(incidencia);
        }

        @Override
        @Transactional
        public void deleteById(Long id) {
                incidenciaRepository.deleteById(id);
        }
}
