package ir.maktab.University.repository;

import ir.maktab.University.entities.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

    Page<Quiz> findAllById(Pageable pageable, long id);
}
