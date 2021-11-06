package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Course;
import ir.maktab.University.repository.CourseRepository;
import ir.maktab.University.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course,Long,CourseRepository> implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getCourseByCourseCode(long courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAllByIsActiveTrue();
    }

}
