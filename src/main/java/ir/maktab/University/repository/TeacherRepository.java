package ir.maktab.University.repository;

import ir.maktab.University.entities.Teacher;
import ir.maktab.University.entities.dto.TeacherDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT new ir.maktab.University.entities.dto.TeacherDTO(" +
            "t.id, t.firstName, t.lastName, t.birthday, t.nationalCode," +
            "t.email, t.gender, t.status) FROM Teacher t")
    List<TeacherDTO> findTeacherDTOs();

    List<Teacher> findAllByIsActiveTrue();

    Teacher findByUserNameAndPassword(String username, String password);

    Teacher findByUserName(String username);
}
