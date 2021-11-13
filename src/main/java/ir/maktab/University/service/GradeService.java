package ir.maktab.University.service;

import ir.maktab.University.entities.Grade;
import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.entities.Questions;

public interface GradeService extends BaseService<Grade,Long> {

    /**
     * Create new object of grade with the point teacher set
     * And save to data base
     * @param point point of the question teacher created
     * @return an object of grade that saved to data base
     */
    Grade createNewGrade(double point);
}
