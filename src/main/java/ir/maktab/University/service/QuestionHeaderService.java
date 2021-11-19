package ir.maktab.University.service;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.entities.QuestionHeader;

public interface QuestionHeaderService extends BaseService<QuestionHeader,Long> {

    /**
     * Create new object of header for the descriptive question teacher created
     * @param descriptive object of descriptive question that saved to data base
     * @param grade the point of the question
     */
    void createNewQuestionHeaderByDescriptive(Descriptive descriptive, double grade);

    /**
     * Create new object of header for the multiple choices question teacher created
     * @param multipleChoices object of multiple choices question that saved to data base
     * @param grade the point of the question
     */
    void createNewQuestionHeaderByMultipleChoices(MultipleChoices multipleChoices, double grade);

    /**
     * Find the question header based on descriptive entity
     * And save it to question
     * @param descriptive the descriptive question question header find by it
     * @param grade point of the question
     */
    void addNewDescriptiveQuestion(Descriptive descriptive,double grade);

    /**
     * Find the question header based on multiple choices entity
     * And save it to question
     * @param multipleChoices the multiple choices question question header find by it
     * @param grade point of the question
     */
    void addNewMultipleChoicesQuestion(MultipleChoices multipleChoices,double grade);
}
