package ir.maktab.University.service;

import ir.maktab.University.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService extends BaseService<Course, Long>{

    Course getCourseByCourseCode(long courseCode);

    List<Course> getAllCourses();
}
