package ru.educationplatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.educationplatform.entity.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignmentId(Long assignmentId);

    List<Submission> findByStudentId(Long studentId);

    Optional<Submission> findByAssignmentIdAndStudentId(Long assignmentId, Long studentId);

    boolean existsByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
}
