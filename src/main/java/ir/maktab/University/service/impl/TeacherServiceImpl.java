package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Teacher;
import ir.maktab.University.repository.TeacherRepository;
import ir.maktab.University.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAllByOrderByStatus();
    }

    @Override
    public Optional<Teacher> getTeacherById(long userId) {
        return teacherRepository.findById(userId);
    }

    @Override
    public void deleteTeacher(long userId) {
        teacherRepository.deleteById(userId);
    }
}
