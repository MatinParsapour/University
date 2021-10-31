package ir.maktab.University.service;

import ir.maktab.University.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    void addStudent(Student student);

    List<Student> getAllStudents();

    Optional<Student> getStudentById(long id);

    void deleteStudent(long id);
}
