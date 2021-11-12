package ir.maktab.University.repository;

import ir.maktab.University.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions,Long> {
}
