package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Article} entities.
 * <p>
 * This interface provides methods for performing CRUD operations on Article entities.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
