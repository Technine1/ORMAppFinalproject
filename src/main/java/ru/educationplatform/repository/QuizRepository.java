package ru.educationplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.educationplatform.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByCourseModuleId(Long courseModuleId);
}
