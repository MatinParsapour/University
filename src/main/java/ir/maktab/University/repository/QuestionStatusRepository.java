package ir.maktab.University.repository;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionStatusRepository extends JpaRepository<QuestionStatus,Long> {

    @Query(value = "SELECT qs FROM QuestionStatus qs JOIN qs.questionHeader qh where qh.id = :id")
    QuestionStatus findQuestionStatusByQuestionHeaderId(@Param("id") long id);
}
