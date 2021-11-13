package ir.maktab.University.service;

import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.entities.QuestionAnswer;

public interface MultipleChoicesService extends BaseService<MultipleChoices,Long> {

    /**
     * Object of multiple choices will save to database
     * @param multipleChoices object of multiple choices includes : title, header(problem), options, correct answer
     * @param grade point of the question
     */
    void createNewMultipleChoices(MultipleChoices multipleChoices, double grade, String option);
}
