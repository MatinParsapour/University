package ir.maktab.University.repository;

import ir.maktab.University.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseCode(long courseCode);
}
