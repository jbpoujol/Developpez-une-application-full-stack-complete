package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.DBUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DBUserRepository extends CrudRepository<DBUser, Long> {

    Optional<DBUser> findByName(String username);

    Optional<DBUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
