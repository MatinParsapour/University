package ir.maktab.University.repository;

import ir.maktab.University.entities.QuestionsBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsBankRepository extends JpaRepository<QuestionsBank,Long> {
}
