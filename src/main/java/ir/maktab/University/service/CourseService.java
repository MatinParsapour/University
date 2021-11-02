package ir.maktab.University.service;

import ir.maktab.University.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    void createCourse(Course course);

    Optional<Course> getCourse(long id);

    List<Course> courses();
}
