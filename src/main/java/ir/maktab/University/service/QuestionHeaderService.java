package ir.maktab.University.service;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.Question;
import ir.maktab.University.entities.QuestionHeader;

public interface QuestionHeaderService extends BaseService<QuestionHeader,Long> {

    /**
     * Create new object of header for the descriptive question teacher created
     * @param descriptive object of descriptive question that saved to data base
     * @param grade the point of the question
     */
    void createNewQuestionHeaderByDescriptive(Descriptive descriptive, double grade);
}
