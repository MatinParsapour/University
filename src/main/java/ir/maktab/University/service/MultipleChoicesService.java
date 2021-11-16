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
     * @param questionId the id of question
     * @param title the title that may changed
     * @param header the problem that may changed
     * @param options the options that may changed
     * @param correctAnswer
     */
    void editQuestion(long questionId, String title, String header, String options, String correctAnswer);

    /**
     * Find the question in the questions bank and set for quiz
     * @param questionId id of the question
     * @param grade point of the question
     */
    void setDescriptiveQuestion(long questionId, double grade);
}
