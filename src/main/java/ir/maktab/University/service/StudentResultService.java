package ir.maktab.University.service;

import ir.maktab.University.entities.QuestionStatus;
import ir.maktab.University.entities.StudentResult;
import ir.maktab.University.entities.dto.extra.StudentAnswersDTO;

public interface StudentResultService extends BaseService<StudentResult,Long> {

    /**
     * save student answers
     * @param questionStatus include question, question point
     * @param studentAnswersDTO include question and student answer
     * @return
     */
    StudentResult saveStudentAnswers(QuestionStatus questionStatus, StudentAnswersDTO studentAnswersDTO);
}
