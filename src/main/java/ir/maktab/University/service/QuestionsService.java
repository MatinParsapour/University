package ir.maktab.University.service;

import ir.maktab.University.entities.Question;
import ir.maktab.University.entities.Questions;

public interface QuestionsService extends BaseService<Questions,Long> {

    /**
     * Create new object of questions include the question and the grade teacher set
     * @param question object of question saved to data base
     * @param grade the point of question
     */
    void addToQuestions(Question question,double grade);

    /**
     * check for quiz if it has a questions object or not
     * @return true if quiz doesn't have questions
     */
    boolean checkQuestions();
}
