package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Course;
import ir.maktab.University.repository.CourseRepository;
import ir.maktab.University.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Optional<Course> getCourse(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> courses() {
        return courseRepository.findAll();
    }

}
