package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Student;
import ir.maktab.University.repository.StudentRepository;
import ir.maktab.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAllByOrderByStatus();
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUserName(username);
    }
}
