package com.TFG.app.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TFG.app.entity.Administrador;


@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
