package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Theme} entities.
 * <p>
 * This interface provides methods for performing CRUD operations on Theme entities.
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
