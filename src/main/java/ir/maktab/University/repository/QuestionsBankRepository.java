package ir.maktab.University.repository;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.QuestionsBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsBankRepository extends JpaRepository<QuestionsBank,Long> {

    QuestionsBank findAllByCourses(Course course);
}
