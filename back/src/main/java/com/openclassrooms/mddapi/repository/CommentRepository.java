package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Comment} entities.
 * <p>
 * This interface provides methods for performing CRUD operations on Comment entities.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
