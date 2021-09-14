package com.TFG.app.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TFG.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
