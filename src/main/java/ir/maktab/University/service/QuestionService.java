package ir.maktab.University.service;

import ir.maktab.University.entities.Question;
import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.Questions;

public interface QuestionService extends BaseService<Question,Long> {

    /**
     * Create new question with question header includes object with type of question teacher created
     * @param questionHeader an object of question header that saved to data base
     * @param grade the point of the question
     */
    void createNewQuestion(QuestionHeader questionHeader,double grade);

    /**
     * Add the question header to question
     * @param questionHeader the question header add to question
     * @param grade the point of question
     */
    void addNewQuestion(QuestionHeader questionHeader, double grade);
}
