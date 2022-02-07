package com.TFG.app.controller;

import com.TFG.app.dto.IncidenciaDto;
import com.TFG.app.dto.Mensaje;
import com.TFG.app.entity.Incidencia;
import com.TFG.app.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Incidencia")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @PreAuthorize("hasRole('ADMIN')")
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


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody IncidenciaDto incidenciaDto){
        if(StringUtils.isBlank(incidenciaDto.getTipo()))
            return new ResponseEntity(new Mensaje("el tipo de incidencia es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(incidenciaDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);

        Incidencia incidencia = new Incidencia(incidenciaDto.getTipo(),incidenciaDto.getDescripcion(),incidenciaDto.getDateInicio(),incidenciaDto.getDateFin(), "Pendiente");
        incidenciaService.save(incidencia);
        return new ResponseEntity(new Mensaje("incidencia creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @PathVariable("estado")String estado,@RequestBody IncidenciaDto incidenciaDto){
        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("esa incidencia no existe"), HttpStatus.NOT_FOUND);


        Incidencia incidencia = incidenciaService.getOne(id).get();
        incidencia.setEstado(incidenciaDto.getEstado());
        incidencia.setDescripcion(incidenciaDto.getDescripcion());
        incidencia.setDateFin(incidenciaDto.getDateFin());
        incidenciaService.save(incidencia);

        return new ResponseEntity(new Mensaje("incidencia actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        incidenciaService.delete(id);
        return new ResponseEntity(new Mensaje("incidencia eliminada"), HttpStatus.OK);
    }
}
