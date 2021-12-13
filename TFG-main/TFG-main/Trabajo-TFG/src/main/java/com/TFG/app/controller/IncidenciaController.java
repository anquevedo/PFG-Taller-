package com.TFG.app.controller;

import com.TFG.app.dto.IncidenciaDto;
import com.TFG.app.dto.Mensaje;
import com.TFG.app.entity.Incidencia;
import com.TFG.app.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Incidencia")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Incidencia>> list(){
        List<Incidencia> list = incidenciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Incidencia> getById(@PathVariable("id") int id){
        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Incidencia incidencia = incidenciaService.getOne(id).get();
        return new ResponseEntity(incidencia, HttpStatus.OK);
    }

    @GetMapping("/numeroIncidencia/{numeroIncidencia}")
    public ResponseEntity<Incidencia> getByNumeroIncidencia(@PathVariable("numeroIncidencia") String numeroIncidencia){
        if(!incidenciaService.existsByNumeroIncidencia(numeroIncidencia))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Incidencia tip = incidenciaService.getByNumeroIncidencia(numeroIncidencia).get();
        return new ResponseEntity(tip, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody IncidenciaDto incidenciaDto){
        if(StringUtils.isBlank(incidenciaDto.getNumeroIncidencia()))
            return new ResponseEntity(new Mensaje("el numero de incidencia es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(incidenciaDto.getTipo()))
            return new ResponseEntity(new Mensaje("el tipo de incidencia es obligatorio"), HttpStatus.BAD_REQUEST);
        if(incidenciaDto.getTiempoEst()==null || incidenciaDto.getTiempoEst()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        Incidencia incidencia = new Incidencia(incidenciaDto.getNumeroIncidencia(), incidenciaDto.getTipo(), incidenciaDto.getTiempoEst(), incidenciaDto.getDescripcion(), false);
        incidenciaService.save(incidencia);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody IncidenciaDto incidenciaDto){
        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(incidenciaService.existsByNumeroIncidencia(incidenciaDto.getNumeroIncidencia()) && incidenciaService.getByNumeroIncidencia(incidenciaDto.getNumeroIncidencia()).get().getId() != id)
            return new ResponseEntity(new Mensaje("esa incidencia ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(incidenciaDto.getNumeroIncidencia()))
            return new ResponseEntity(new Mensaje("el tiempo"), HttpStatus.BAD_REQUEST);
        if(incidenciaDto.getTiempoEst()==null || incidenciaDto.getTiempoEst()<0 )
            return new ResponseEntity(new Mensaje("el tiempo ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Incidencia incidencia = incidenciaService.getOne(id).get();
        incidencia.setNumeroIncidencia(incidenciaDto.getNumeroIncidencia());
        incidencia.setTiempoEst(incidenciaDto.getTiempoEst());
        incidencia.setSolucionada(incidenciaDto.isSolucionada());
        incidenciaService.save(incidencia);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        incidenciaService.delete(id);
        return new ResponseEntity(new Mensaje("incidencia eliminada"), HttpStatus.OK);
    }
}
