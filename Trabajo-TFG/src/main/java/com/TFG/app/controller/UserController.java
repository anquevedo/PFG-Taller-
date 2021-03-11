package com.TFG.app.controller;

import com.TFG.app.entity.User;
import com.TFG.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Crear nuevo usuario
    @PostMapping
    public ResponseEntity<?> create (@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    //Leer usuario
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value= "id") Long UserId){
        Optional<User> oUser = userService.findById(UserId);

        if(!oUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }

    //Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody User userDetails, @PathVariable (value="id")Long userId){
        Optional<User> user= userService.findById(userId);

        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }

        //Copiar todo entero si queremos actualizar todo: BeanUtils.copyProperties(userDetails, user.get());
        user.get().setName(userDetails.getName());
        user.get().setSurname(userDetails.getSurname());
        user.get().setEmail(userDetails.getEmail());
        user.get().setEnabled(userDetails.getEnabled());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));

    }

    //Borrar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable(value = "id")Long userId){
        if(!userService.findById(userId).isPresent()){
            return ResponseEntity.notFound().build();
        }

        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    //Leer todos los usuarios
    @GetMapping
    public List<User> readAll(){
        List<User> users= StreamSupport
                .stream(userService.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return users;
    }
}
