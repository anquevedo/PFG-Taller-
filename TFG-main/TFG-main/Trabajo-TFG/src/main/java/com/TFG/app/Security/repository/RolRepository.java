package com.TFG.app.Security.repository;

import com.TFG.app.Security.entity.Rol;
import com.TFG.app.Security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
