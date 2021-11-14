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
     * @param questionId id of the descriptive question
     * @param title the title that may changed
     * @param header the header that may changed
     * @param correctAnswer the correct answer that may changed
     */
    void editQuestion(long questionId, String title, String header, String correctAnswer);
}
