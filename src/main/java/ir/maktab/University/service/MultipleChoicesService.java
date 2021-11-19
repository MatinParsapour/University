package ir.maktab.University.service;

import ir.maktab.University.entities.MultipleChoices;

public interface MultipleChoicesService extends BaseService<MultipleChoices,Long> {

    /**
     * Object of multiple choices will save to database
     * @param multipleChoices object of multiple choices includes : title, header(problem), options, correct answer
     * @param correctAnswer the correct answer teacher selected
     * @param grade point of the question
     */
    void createNewMultipleChoices(MultipleChoices multipleChoices, double grade, String option,String correctAnswer);

    /**
     * Get all data from controller and set it to multiple choices question and save it
     * @param multipleChoices the question with changes come here
     */
    void editQuestion(MultipleChoices multipleChoices);

    /**
     * Find the question in the questions bank and set for quiz
     * @param questionId id of the question
     * @param grade point of the question
     */
    void setDescriptiveQuestion(long questionId, double grade);
}
