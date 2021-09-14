package com.TFG.app.controller;

import com.TFG.app.entity.Administrador;
import com.TFG.app.service.AdministradorService;
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
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    //Crear nuevo Administrador
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Administrador administrador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.save(administrador));
    }

    //Leer Administrador
    @GetMapping("/{dni}")
    public ResponseEntity<?> read(@PathVariable(value = "dni") Long administradorDni) {
        Optional<Administrador> oAdministrador = administradorService.findByDni(administradorDni);

        if (!oAdministrador.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oAdministrador);
    }

    //Actualizar Administrador
    @PutMapping("/{dni}")
    public ResponseEntity<?> update(@RequestBody Administrador administradorDetails, @PathVariable(value = "dni") Long administradorDni) {
        Optional<Administrador> administrador = administradorService.findByDni(administradorDni);

        if (!administrador.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        //Copiar todo entero si queremos actualizar todo: BeanUtils.copyProperties(userDetails, user.get());
        administrador.get().setName(administradorDetails.getName());
        administrador.get().setEmail(administradorDetails.getEmail());
        administrador.get().setPassword(administradorDetails.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.save(administrador.get()));

    }

    //Borrar Administrador
    @DeleteMapping("/{dni}")
    public ResponseEntity<?> delete(@PathVariable(value = "dni") Long administradorDni) {
        if (!administradorService.findByDni(administradorDni).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        administradorService.deleteByDni(administradorDni);
        return ResponseEntity.ok().build();
    }

    //Leer todos los Administradores
    @GetMapping
    public List<Administrador> readAll() {
        List<Administrador> administrador = StreamSupport
                .stream(administradorService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return administrador;
    }
}
