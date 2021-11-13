package ir.maktab.University.service;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionsBank;

public interface QuestionsBankService extends BaseService<QuestionsBank,Long> {

    /**
     * Add the question header includes one of the question types to teacher question bank
     * And if teacher don't have any question bank creates a new question bank
     * @param questionHeader the question that has been created
     */
    void addQuestionHeaderToQuestionBank(QuestionHeader questionHeader);

    /**
     * Check if teacher has question bank
     * @return true if teacher has a question bank
     */
    boolean checkTeacherQuestionsBank();
}
