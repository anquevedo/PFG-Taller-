package com.TFG.app.Security.service;


import com.TFG.app.Security.entity.Usuario;
import com.TFG.app.Security.repository.UsuarioRepository;
import com.TFG.app.entity.Coche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail){
        return usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail);
    }

    public Optional<Usuario> getByTokenPassword(String tokenPassword){
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public List<Usuario> findMecanicos() {return usuarioRepository.findMecanicos(); }

    public List<Usuario> findUsuarios() {return usuarioRepository.findUsuarios(); }

    public int buscarNombreUsuario(String nombreUsuario){
        return usuarioRepository.buscarNombreUsuario(nombreUsuario);
    }

    public void deleteRol(int id){
        usuarioRepository.deleteByRol(id);
    }

    public void deleteUsuario(int id){
        usuarioRepository.deleteByUsuario(id);

    }
    public void deleteCoches(String nombreUsuario) {
        usuarioRepository.deleteCoches(nombreUsuario);
    }
    public void deleteIncidencias(String nombreUsuario){
        usuarioRepository.deleteIncidencias(nombreUsuario);
    }
    public void updateIncidencia(String nombreUsuario){
        usuarioRepository.updateIncidencia(nombreUsuario);
    }
    public void updateIncidenciaSelec(String nombreUsuario){
        usuarioRepository.updateIncidenciaSelec(nombreUsuario);
    }

}
