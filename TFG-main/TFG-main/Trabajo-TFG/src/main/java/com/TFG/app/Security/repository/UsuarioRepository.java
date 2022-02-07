package com.TFG.app.Security.repository;

import com.TFG.app.Security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByNombreUsuarioOrEmail(String nombreUsuario, String email);
    Optional<Usuario> findByTokenPassword(String tokenPassword);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);

    @Query(nativeQuery =true ,value ="SELECT * FROM usuario u, usuario_rol r WHERE u.id=r.usuario_id and r.rol_id=(3)")
    public List<Usuario> find();

    @Query(nativeQuery =true ,value ="SELECT u.id FROM usuario u WHERE u.nombre_usuario=:nombreUsuario")
    public int buscarNombreUsuario (@PathVariable ("nombreUsuario") String nombreUsuario);

    @Modifying
    @Query(nativeQuery =true ,value ="DELETE ur FROM usuario_rol ur WHERE ur.usuario_id =:id")
    public void deleteByRol (@PathVariable ("id") int id);


    @Modifying
    @Query(nativeQuery =true ,value ="DELETE u FROM usuario u WHERE u.id =:id")
    public void deleteByUsuario (@PathVariable ("id") int id);



}
