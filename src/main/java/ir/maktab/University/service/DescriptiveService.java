package ir.maktab.University.service;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.Grade;

public interface DescriptiveService extends BaseService<Descriptive,Long> {

    /**
     * Object of descriptive will save to database
     * @param descriptive object of descriptive includes : title, header(problem), correct answer
     * @param grade point of the question
     */
    void createNewDescriptive(Descriptive descriptive, double grade);
}
