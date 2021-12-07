package com.TFG.app.controller;

import com.TFG.app.entity.Incidencia;
import com.TFG.app.service.IncidenciaService;
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
@RequestMapping("/api/incidencia")
public class IncidenciaController {

        @Autowired
        private IncidenciaService incidenciaService;

        //Crear nueva incidencia
        @PostMapping
        public ResponseEntity<?> create (@RequestBody Incidencia incidencia) {
            return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaService.save(incidencia));
        }
        //Leer incidencia
        @GetMapping("/{id}")
        public ResponseEntity<?> read(@PathVariable(value= "id") Long IncidenciaId){
            Optional<Incidencia> oIncidencia = incidenciaService.findById(IncidenciaId);

            if(!oIncidencia.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(oIncidencia);
        }




        //Actualizar incidencia
        @PutMapping("/{id}")
        public ResponseEntity<?> update (@RequestBody Incidencia incidenciaDetails, @PathVariable (value="id")Long incidenciaId){
            Optional<Incidencia> incidencia= incidenciaService.findById(incidenciaId);

            if(!incidencia.isPresent()){
                return ResponseEntity.notFound().build();
            }

            //Copiar todo entero si queremos actualizar todo: BeanUtils.copyProperties(userDetails, user.get());
            incidencia.get().setDesc(incidenciaDetails.getDesc());
            incidencia.get().setSolucionada(incidenciaDetails.getSolucionada());
            incidencia.get().setTiempoEst(incidenciaDetails.getTiempoEst());
            incidencia.get().setTipo(incidenciaDetails.getTipo());


            return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaService.save(incidencia.get()));

        }

        //Borrar usuario
        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete (@PathVariable(value = "id")Long incidenciaId){
            if(!incidenciaService.findById(incidenciaId).isPresent()){
                return ResponseEntity.notFound().build();
            }

            incidenciaService.deleteById(incidenciaId);
            return ResponseEntity.ok().build();
        }

        //Leer todos los usuarios
        @GetMapping
        public List<Incidencia> readAll(){
            List<Incidencia> incidencias= StreamSupport
                    .stream(incidenciaService.findAll().spliterator(),false)
                    .collect(Collectors.toList());
            return incidencias;
        }
}
