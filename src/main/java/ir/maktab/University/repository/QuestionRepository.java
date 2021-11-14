package ir.maktab.University.repository;

import ir.maktab.University.entities.Question;
import ir.maktab.University.entities.QuestionHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    Question findByQuestionHeader(QuestionHeader questionHeader);
}
