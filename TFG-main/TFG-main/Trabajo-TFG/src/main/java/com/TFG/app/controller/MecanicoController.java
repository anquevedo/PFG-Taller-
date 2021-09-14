package com.TFG.app.controller;

import com.TFG.app.entity.Mecanico;
import com.TFG.app.service.MecanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/mecanico")
public class MecanicoController {

        @Autowired
        private MecanicoService mecanicoService;

        //Crear nuevo mecanico
        @CrossOrigin(origins = "http://localhost:4200")
        @PostMapping
        public ResponseEntity<?> create(@RequestBody Mecanico mecanico) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mecanicoService.save(mecanico));
        }

        //Leer mecanico
        @GetMapping("/{dni}")
        public ResponseEntity<?> read(@PathVariable(value = "dni") Long MecanicoDni) {
            Optional<Mecanico> oMecanico = mecanicoService.findByDni(MecanicoDni);

            if (!oMecanico.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(oMecanico);
        }

        //Actualizar mecanico
        @PutMapping("/{dni}")
        public ResponseEntity<?> update(@RequestBody Mecanico mecanicoDetails, @PathVariable(value = "dni") Long MecanicoDni) {
            Optional<Mecanico> mecanico = mecanicoService.findByDni(MecanicoDni);

            if (!mecanico.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            //Copiar todo entero si queremos actualizar todo: BeanUtils.copyProperties(userDetails, user.get());
            mecanico.get().setName(mecanicoDetails.getName());
            mecanico.get().setSurname(mecanicoDetails.getSurname());
            mecanico.get().setEmail(mecanicoDetails.getEmail());
            mecanico.get().setPassword(mecanicoDetails.getPassword());
            mecanico.get().setEnabled(mecanicoDetails.getEnabled());


            return ResponseEntity.status(HttpStatus.CREATED).body(mecanicoService.save(mecanico.get()));

        }

        //Borrar mecanico
        @DeleteMapping("/{dni}")
        public ResponseEntity<?> delete(@PathVariable(value = "dni") Long mecanicoDni) {
            if (!mecanicoService.findByDni(mecanicoDni).isPresent()) {
                return ResponseEntity.notFound().build();
            }

            mecanicoService.deleteByDni(mecanicoDni);
            return ResponseEntity.ok().build();
        }

        //Leer todos los mecanico
        @CrossOrigin(origins = "http://localhost:4200")
        @GetMapping
        public List<Mecanico> readAll() {
            List<Mecanico> mecanico = StreamSupport
                    .stream(mecanicoService.findAll().spliterator(), false)
                    .collect(Collectors.toList());
            return mecanico;
        }
    }

