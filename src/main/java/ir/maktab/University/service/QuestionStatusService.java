package ir.maktab.University.service;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionStatus;
import ir.maktab.University.entities.dto.extra.StudentAnswersDTO;

import java.util.List;
import java.util.Map;

public interface QuestionStatusService extends BaseService<QuestionStatus,Long> {

    QuestionStatus createNewQuestionStatus(QuestionHeader questionHeader, double grade);

    /**
     * Get all of student answers and save to data base
     * @param studentAnswers all of student answers
     */
    void setResult(List<StudentAnswersDTO> studentAnswers);

    /**
     * Get question status by student result id
     * @param studentResultId the id of student result
     * @return Question status
     */
    QuestionStatus getQuestionStatusByStudentResult(long studentResultId);
}
