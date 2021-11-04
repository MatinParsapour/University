package ir.maktab.University.repository;

import ir.maktab.University.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseCode(long courseCode);

    List<Course> findAllByIsActiveTrue();
}
