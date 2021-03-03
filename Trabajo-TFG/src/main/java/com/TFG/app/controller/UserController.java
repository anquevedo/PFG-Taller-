package com.TFG.app.controller;

import com.TFG.app.entity.User;
import com.TFG.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/(id(")
    public ResponseEntity<?> read(@PathVariable(value= "id") Long UserId){
        Optional<User> oUser = userService.findById(UserId);

        if(!oUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }
}
