package com.TFG.app.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TFG.app.entity.Mecanico;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, Long>{

}
