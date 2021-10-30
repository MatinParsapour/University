package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Teacher;
import ir.maktab.University.entities.User;
import ir.maktab.University.repository.TeacherRepository;
import ir.maktab.University.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
