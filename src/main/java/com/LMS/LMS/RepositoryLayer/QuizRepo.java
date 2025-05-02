package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface QuizRepo extends JpaRepository<Quiz, Long> {
    @Query("SELECT q FROM Quiz q WHERE q.id = :id")
    Optional<Quiz> findById(Long id);
}