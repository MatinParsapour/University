package ir.maktab.University.service;

import ir.maktab.University.entities.Questions;
import ir.maktab.University.entities.Quiz;

public interface QuizService extends BaseService<Quiz, Long> {

    /**
     * Set quiz in data base and relate it to the course that teacher chose
     * It will roll back if during creating course disorder occurs
     *
     * @param quiz     the quiz that teacher created
     * @param courseId the id of course that the quiz relate to
     * @return The Quiz object that set in data base
     */
    Quiz createQuiz(Quiz quiz, long courseId);

    /**
     * Edit the information about a quiz that teacher created
     *
     * @param quizId      the id of quiz that teacher chose
     * @param title       The title that may changed
     * @param description The description that may changed
     * @param quizTime    the time of quiz that may changed
     */
    void editQuiz(long quizId, String title, String description, Double quizTime);

    /**
     * Add new object of questions to quiz
     * @param questions object of questions includes an object of question and object of grade
     */
    void addQuestions(Questions questions);
}
