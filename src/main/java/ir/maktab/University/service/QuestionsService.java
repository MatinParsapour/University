package ir.maktab.University.service;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.Questions;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.entities.dto.extra.QuestionHeaderDTO;

import java.util.Set;

public interface QuestionsService extends BaseService<Questions,Long> {

    /**
     * Create new object of questions include the question and the grade teacher set
     * @param questionHeader object of question header saved to data base
     * @param grade the point of question
     */
    void addToQuestions(QuestionHeader questionHeader, double grade);

    /**
     * Get all exam questions and create DTO
     * @param questions exam questions
     * @return a Set of DTOs of questions
     */
    Set<QuestionHeaderDTO> questionHeaders(Set<Questions> questions);
}
