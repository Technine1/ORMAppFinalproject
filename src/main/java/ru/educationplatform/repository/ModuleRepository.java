package ru.educationplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.educationplatform.entity.CourseModule;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModule, Long> {
    List<CourseModule> findByCourseIdOrderByOrderIndex(Long courseId);
}
