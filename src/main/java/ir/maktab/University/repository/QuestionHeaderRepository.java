package ir.maktab.University.repository;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.entities.QuestionHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionHeaderRepository extends JpaRepository<QuestionHeader,Long> {

    QuestionHeader findByDescriptive(Descriptive descriptive);

    QuestionHeader findByMultipleChoices(MultipleChoices multipleChoices);
}

