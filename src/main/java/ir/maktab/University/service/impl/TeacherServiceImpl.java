package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Teacher;
import ir.maktab.University.repository.TeacherRepository;
import ir.maktab.University.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Long,TeacherRepository> implements TeacherService {


    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }
    @Override
    public Teacher getTeacherByUserName(String username) {
        return teacherRepository.findByUserName(username);
    }
}
