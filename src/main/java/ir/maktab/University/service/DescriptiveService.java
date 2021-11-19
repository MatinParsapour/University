package ir.maktab.University.service;

import ir.maktab.University.entities.Descriptive;

public interface DescriptiveService extends BaseService<Descriptive,Long> {

    /**
     * Object of descriptive will save to database
     * @param descriptive object of descriptive includes : title, header(problem), correct answer
     * @param grade point of the question
     */
    void createNewDescriptive(Descriptive descriptive, double grade);

    /**
     * Get all the data from controller and set it to descriptive question and save it
     * @param descriptive the question with changes come here
     */
    void editQuestion(Descriptive descriptive);

    /**
     * Find the question in the questions bank and set for quiz
     * @param questionId id of the question
     * @param grade point of the question
     */
    void setDescriptiveQuestion(long questionId, double grade);
}
