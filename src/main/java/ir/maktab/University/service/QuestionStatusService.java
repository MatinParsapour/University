package ir.maktab.University.service;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionStatus;

public interface QuestionStatusService extends BaseService<QuestionStatus,Long> {

    QuestionStatus createNewQuestionStatus(QuestionHeader questionHeader, double grade);
}
