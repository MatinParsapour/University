package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Student;
import ir.maktab.University.repository.StudentRepository;
import ir.maktab.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,Long,StudentRepository> implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUserName(username);
    }
}
