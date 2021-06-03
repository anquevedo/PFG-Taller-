package com.TFG.app.controller;

import com.TFG.app.entity.Cliente;
import com.TFG.app.service.ClienteService;
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
    @RequestMapping("/api/cliente")
    public class ClienteController {

        @Autowired
        private ClienteService clienteService;

        //Crear nuevo cliente
        @CrossOrigin(origins = "http://localhost:4200")
        @PostMapping
        public ResponseEntity<?> create(@RequestBody Cliente cliente) {
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
        }

        //Leer cliente
        @GetMapping("/{dni}")
        public ResponseEntity<?> read(@PathVariable(value = "dni") Long ClienteDni) {
            Optional<Cliente> oCliente = clienteService.findByDni(ClienteDni);

            if (!oCliente.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(oCliente);
        }

        //Actualizar cliente
        @PutMapping("/{dni}")
        public ResponseEntity<?> update(@RequestBody Cliente clienteDetails, @PathVariable(value = "dni") Long clienteDni) {
            Optional<Cliente> cliente = clienteService.findByDni(clienteDni);

            if (!cliente.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            //Copiar todo entero si queremos actualizar todo: BeanUtils.copyProperties(userDetails, user.get());
            cliente.get().setName(clienteDetails.getName());
            cliente.get().setSurname(clienteDetails.getSurname());
            cliente.get().setEmail(clienteDetails.getEmail());
            cliente.get().setEnabled(clienteDetails.getEnabled());

            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente.get()));

        }

        //Borrar cliente
        @DeleteMapping("/{dni}")
        public ResponseEntity<?> delete(@PathVariable(value = "dni") Long clienteDni) {
            if (!clienteService.findByDni(clienteDni).isPresent()) {
                return ResponseEntity.notFound().build();
            }

            clienteService.deleteByDni(clienteDni);
            return ResponseEntity.ok().build();
        }

        //Leer todos los clientes
        @CrossOrigin(origins = "http://localhost:4200")
        @GetMapping
        public List<Cliente> readAll() {
            List<Cliente> clientes = StreamSupport
                    .stream(clienteService.findAll().spliterator(), false)
                    .collect(Collectors.toList());
            return clientes;
        }
}
