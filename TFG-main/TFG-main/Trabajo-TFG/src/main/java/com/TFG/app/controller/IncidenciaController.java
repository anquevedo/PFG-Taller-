package com.TFG.app.controller;

import com.TFG.app.dto.IncidenciaDto;
import com.TFG.app.dto.Mensaje;
import com.TFG.app.entity.Coche;
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
@RequestMapping("/Incidencia")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Incidencia>> list(){
        List<Incidencia> list = incidenciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/listaPorNombreMecanico/{nombreUsuario}")
    public ResponseEntity<List<Incidencia>> listaPorNombreMecanico(@PathVariable("nombreUsuario") String nombreUsuario) {
        List<Incidencia> list = incidenciaService.list();

        for (int i = 0; i < list.size(); i++) {
            String nombreUs= list.get(i).getNombreMecanico();

            if (!nombreUs.equals(nombreUsuario)) {
                list.remove(i);
                i--;
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/listaPorNombreUsuario/{nombreUsuario}")
    public ResponseEntity<List<Incidencia>> listaPorNombre(@PathVariable("nombreUsuario") String nombreUsuario) {
        List<Incidencia> list = incidenciaService.list();

        for (int i = 0; i < list.size(); i++) {
            String nombreUs= list.get(i).getNombreUsuario();

            if (!nombreUs.equals(nombreUsuario)) {
                list.remove(i);
                i--;
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/listaSinSeleccionar/")
    public ResponseEntity<List<Incidencia>> listaSinSeleccionar() {
        List<Incidencia> list = incidenciaService.list();

        for (int i = 0; i < list.size(); i++) {

            if (!list.get(i).getNombreMecanico().equals("")) {
                list.remove(i);
                i--;
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/listaId/{id}")
    public ResponseEntity<List<Incidencia>> listId(@PathVariable("id") int id) {
        List<Incidencia> listAux = incidenciaService.list();

        for (int i = 0; i < listAux.size(); i++) {
            if (listAux.get(i).getId() != id || listAux.get(i).getSeleccionada() !=false) {
                listAux.remove(i);
                i--;
            }
        }

        return new ResponseEntity(listAux, HttpStatus.OK);

    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody IncidenciaDto incidenciaDto){
        if(StringUtils.isBlank(incidenciaDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);

        List<Incidencia> list = incidenciaService.list();
        String numeroMatricula= incidenciaDto.getMatriculaCoche();
        String nombreUsuario= incidenciaDto.getNombreUsuario();

        if(incidenciaService.comprobarMatricula(numeroMatricula) < 1)
            return new ResponseEntity(new Mensaje("no existe ese coche"), HttpStatus.ACCEPTED);

        int count= 0;
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getNombreUsuario().equals(incidenciaDto.getNombreUsuario())) {
                count++;
            }
            if (count==5){
                return new ResponseEntity(new Mensaje("No se pueden crear mas incidencias"), HttpStatus.NOT_FOUND);
            }
        }
        Incidencia incidencia = new Incidencia(incidenciaDto.getDescripcion(),incidenciaDto.getDateInicio(),
                incidenciaDto.getDateFin(), "Pendiente", incidenciaDto.getNombreUsuario(), false, "", incidenciaDto.getMatriculaCoche());
        incidenciaService.save(incidencia);
        return new ResponseEntity(new Mensaje("incidencia creado"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Coche> getById(@PathVariable("id") int id){
        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Incidencia incidencia = incidenciaService.getOne(id).get();
        return new ResponseEntity(incidencia, HttpStatus.OK);
    }

    @PutMapping("/updateEstado/{id}")
    public ResponseEntity<?> updateEstado(@PathVariable("id")int id, @RequestBody IncidenciaDto incidenciaDto){
        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("esa incidencia no existe"), HttpStatus.NOT_FOUND);


        Incidencia incidencia = incidenciaService.getOne(id).get();
        incidencia.setEstado(incidenciaDto.getEstado());
        incidencia.setDateInicio(incidenciaDto.getDateInicio());
        incidencia.setDateFin(incidenciaDto.getDateFin());

        incidenciaService.save(incidencia);

        return new ResponseEntity(new Mensaje("incidencia actualizado"), HttpStatus.OK);
    }

    @PutMapping("/seleccionarIncidencia/{id}/{nombreUsuario}")
    public ResponseEntity<?> seleccionarIncidencia( @PathVariable("id")int id, @PathVariable("nombreUsuario")String nombreUsuario){

        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("esa incidencia no existe"), HttpStatus.NOT_FOUND);

        Incidencia incidencia = incidenciaService.getOne(id).get();
        incidencia.setSeleccionada(true);
        incidencia.setNombreMecanico(nombreUsuario);
        incidencia.setEstado("En Proceso");
        incidenciaService.save(incidencia);

        return new ResponseEntity(new Mensaje("Has seleccionado la incidencia"), HttpStatus.OK);

    }

    @PutMapping("/quitarIncidencia/{id}/{nombreUsuario}")
    public ResponseEntity<?> quitarIncidencia( @PathVariable("id")int id, @PathVariable("nombreUsuario")String nombreUsuario){

        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("esa incidencia no existe"), HttpStatus.NOT_FOUND);

        Incidencia incidencia = incidenciaService.getOne(id).get();
        incidencia.setSeleccionada(false);
        incidencia.setNombreMecanico("");
        incidencia.setEstado("Pendiente");
        incidenciaService.save(incidencia);

        return new ResponseEntity(new Mensaje("Has quitado la incidencia"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!incidenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        incidenciaService.delete(id);
        return new ResponseEntity(new Mensaje("incidencia eliminada"), HttpStatus.OK);
    }
}
