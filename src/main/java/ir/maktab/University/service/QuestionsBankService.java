package ir.maktab.University.service;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionsBank;

import java.util.List;

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

    /**
     * Check if questions bank already have a course or not
     * @param questionsBank the courses in this questions bank
     * @return true if the course exists
     */
    boolean checkQuestionsBankCourses(QuestionsBank questionsBank);

    /**
     * Find all the courses by quiz id
     * Then find all questions bank by course
     * @param quizId the id of quiz
     * @return a questions bank contains the course
     */
    QuestionsBank getAllQuestionsBank(long quizId);
}
