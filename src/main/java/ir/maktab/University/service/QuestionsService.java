package ir.maktab.University.service;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.Questions;

public interface QuestionsService extends BaseService<Questions,Long> {

    /**
     * Create new object of questions include the question and the grade teacher set
     * @param questionHeader object of question header saved to data base
     * @param grade the point of question
     */
    void addToQuestions(QuestionHeader questionHeader, double grade);
}
