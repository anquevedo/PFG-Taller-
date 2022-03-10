package com.TFG.app.Security.controller;

import com.TFG.app.Security.dto.JwtDto;
import com.TFG.app.Security.dto.LoginUsuario;
import com.TFG.app.Security.dto.NuevoUsuario;
import com.TFG.app.Security.entity.Rol;
import com.TFG.app.Security.entity.Usuario;
import com.TFG.app.Security.enums.RolNombre;
import com.TFG.app.Security.jwt.JwtProvider;
import com.TFG.app.Security.service.RolService;
import com.TFG.app.Security.service.UsuarioService;
import com.TFG.app.dto.Mensaje;
import com.TFG.app.entity.Coche;
import com.TFG.app.entity.Incidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        System.out.println(nuevoUsuario);
        System.out.println(nuevoUsuario.getRoles());

        Set<Rol> roles = new HashSet<>();
        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_MECANICO).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USUARIO).get());
        }
        else if (nuevoUsuario.getRoles().contains("user"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USUARIO).get());
        else if(nuevoUsuario.getRoles().contains("mecanico"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_MECANICO).get());

        usuario.setRoles(roles);

        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/nuevoUsuario")
    public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        System.out.println(nuevoUsuario);
        System.out.println(nuevoUsuario.getRoles());

        Set<Rol> roles = new HashSet<>();
        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_MECANICO).get());
        }
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USUARIO).get());

        usuario.setRoles(roles);

        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/nuevoMecanico")
    public ResponseEntity<?> nuevoMecanico(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        System.out.println(nuevoUsuario);
        System.out.println(nuevoUsuario.getRoles());

        Set<Rol> roles = new HashSet<>();
        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USUARIO).get());
        }

        roles.add(rolService.getByRolNombre(RolNombre.ROLE_MECANICO).get());

        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/getMecanicos")
    public ResponseEntity<List<Usuario>> listMecanicos() {
        List<Usuario> list = usuarioService.findMecanicos();
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).getNombre().equals("admin")) {
                list.remove(i);
                i--;
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/getUsuarios")
    public ResponseEntity<List<Usuario>> listUsuarios() {
        List<Usuario> list = usuarioService.findUsuarios();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNombre().equals("admin")) {
                list.remove(i);
                i--;
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }



    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")String nombreUsuario){
        if(!usuarioService.existsByNombreUsuario(nombreUsuario))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        int id = usuarioService.buscarNombreUsuario(nombreUsuario);
        System.out.println(id);
        usuarioService.deleteRol(id);
        usuarioService.deleteCoches(nombreUsuario);
        usuarioService.updateIncidenciaSelec(nombreUsuario);
        usuarioService.updateIncidencia(nombreUsuario);
        usuarioService.deleteIncidencias(nombreUsuario);
        usuarioService.deleteUsuario(id);

        return new ResponseEntity(new Mensaje("coche eliminado"), HttpStatus.OK);
    }
}
