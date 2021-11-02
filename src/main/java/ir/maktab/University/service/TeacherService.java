package ir.maktab.University.service;

import ir.maktab.University.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    void addTeacher(Teacher teacher);

    List<Teacher> teachers();

    Optional<Teacher> getTeacherById(long userId);

    void deleteTeacher(long userId);

    Teacher getTeacherByUserName(String username);
}
