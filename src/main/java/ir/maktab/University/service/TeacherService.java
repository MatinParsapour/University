package ir.maktab.University.service;

import ir.maktab.University.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService extends BaseService<Teacher,Long> {

    Teacher getTeacherByUserName(String username);

    List<Teacher> getAllTeachers();
}
