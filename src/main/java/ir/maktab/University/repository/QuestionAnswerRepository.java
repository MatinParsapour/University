package ir.maktab.University.repository;

import ir.maktab.University.entities.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {
}
