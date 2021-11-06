package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Student;
import ir.maktab.University.repository.StudentRepository;
import ir.maktab.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,Long,StudentRepository> implements StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUserName(username);
    }

    @Override
    public Student getStudentByUserNameAndPassword(String username, String password) {
        return studentRepository.findByUserNameAndPassword(username, password);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAllByIsActiveTrue();
    }
}
