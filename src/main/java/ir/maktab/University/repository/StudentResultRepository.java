package ir.maktab.University.repository;

import ir.maktab.University.entities.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StudentResultRepository extends JpaRepository<StudentResult,Long> {
    Set<StudentResult> findByStudentId(Long id);
}
