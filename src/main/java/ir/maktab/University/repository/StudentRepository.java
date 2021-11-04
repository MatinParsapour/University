package ir.maktab.University.repository;

import ir.maktab.University.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByOrderByStatus();

    Student findByUserName(String username);
}
