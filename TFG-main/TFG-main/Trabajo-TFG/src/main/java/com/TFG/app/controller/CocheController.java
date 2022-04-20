package com.TFG.app.controller;

import com.TFG.app.dto.CocheDto;
import com.TFG.app.dto.Mensaje;
import com.TFG.app.entity.Coche;
import com.TFG.app.entity.Incidencia;
import com.TFG.app.service.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Coche")
public class CocheController {
        @Autowired
        private CocheService cocheService;

    @PreAuthorize("hasRole('USUARIO')")
    @GetMapping("/lista")
    public ResponseEntity<List<Coche>> list(){
        List<Coche> list = cocheService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/listaPorNombre/{nombreUsuario}")
    public ResponseEntity<List<Coche>> listaPorNombre(@PathVariable("nombreUsuario") String nombreUsuario) {
        List<Coche> list = cocheService.list();

        for (int i = 0; i < list.size(); i++) {
            String nombreUs= list.get(i).getNombreUsuario();

            if (!nombreUs.equals(nombreUsuario)) {
                list.remove(i);
                i--;
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Coche> getById(@PathVariable("id") int id){
        if(!cocheService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Coche coche = cocheService.getOne(id).get();
        return new ResponseEntity(coche, HttpStatus.OK);
    }

    @GetMapping("/detailname/{matricula}")
    public ResponseEntity<Coche> getByNombre(@PathVariable("matricula") String matricula){
        if(!cocheService.existsByMatricula(matricula))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Coche coche = cocheService.getByMatricula(matricula).get();
        return new ResponseEntity(coche, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USUARIO')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CocheDto cocheDto){
        if(StringUtils.isBlank(cocheDto.getMatricula()))
            return new ResponseEntity(new Mensaje("la matricula es obligatoria"), HttpStatus.BAD_REQUEST);
        if(cocheDto.getPrecio()==null || cocheDto.getPrecio()<1 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(cocheService.existsByMatricula(cocheDto.getMatricula()))
            return new ResponseEntity(new Mensaje("esa matricula ya existe"), HttpStatus.ACCEPTED);
        System.out.println(cocheDto.getNombreUsuario());
        Coche coche = new Coche(cocheDto.getMatricula(), cocheDto.getMarca(), cocheDto.getModelo(), cocheDto.getAnio(),cocheDto.getPrecio(), cocheDto.getNombreUsuario());
        cocheService.save(coche);
        return new ResponseEntity(new Mensaje("coche creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody CocheDto cocheDto){
        if(!cocheService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(cocheService.existsByMatricula(cocheDto.getMatricula()) && cocheService.getByMatricula(cocheDto.getMatricula()).get().getId() != id)
            return new ResponseEntity(new Mensaje("esa matricula ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(cocheDto.getMatricula()))
            return new ResponseEntity(new Mensaje("la matricula es obligatoria"), HttpStatus.BAD_REQUEST);
        if(cocheDto.getPrecio()==null || cocheDto.getPrecio()<1 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Coche coche = cocheService.getOne(id).get();
        coche.setMarca(cocheDto.getMarca());
        coche.setModelo(cocheDto.getModelo());
        coche.setPrecio(cocheDto.getPrecio());
        coche.setAnio(cocheDto.getAnio());
        cocheService.save(coche);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){

        if(!cocheService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        String matricula = cocheService.buscarMatricula(id);
        cocheService.borrarIncidencia(matricula);
        cocheService.delete(id);
        return new ResponseEntity(new Mensaje("mecanico eliminado"), HttpStatus.OK);
    }


}



