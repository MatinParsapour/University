package ir.maktab.University.repository;

import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT new ir.maktab.University.entities.dto.StudentDTO(" +
            "s.id, s.firstName, s.lastName, s.birthday," +
            "s.nationalCode, s.email, s.gender, s.status)" +
            "FROM Student s")
    List<StudentDTO> findStudentDTOs();

    List<Student> findAllByIsActiveTrue();

    Student findByUserName(String username);

    Student findByUserNameAndPassword(String usename, String password);
}

