package ir.maktab.University.service;

import ir.maktab.University.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService extends BaseService<Student,Long>{

    Student getStudentByUsername(String username);

    List<Student> getAllStudents();
}
