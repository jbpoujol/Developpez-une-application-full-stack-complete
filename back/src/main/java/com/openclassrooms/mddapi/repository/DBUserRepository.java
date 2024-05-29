package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.DBUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for {@link DBUser} entities.
 * <p>
 * This interface provides methods for performing CRUD operations on DBUser entities,
 * as well as custom queries for finding users by name or email and checking for the existence of a user by email.
 */
public interface DBUserRepository extends CrudRepository<DBUser, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the found user, or an empty Optional if no user was found
     */
    Optional<DBUser> findByName(String username);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the found user, or an empty Optional if no user was found
     */
    Optional<DBUser> findByEmail(String email);

    /**
     * Checks if a user exists by their email address.
     *
     * @param email the email address to check
     * @return true if a user exists with the given email address, false otherwise
     */
    boolean existsByEmail(String email);
}
