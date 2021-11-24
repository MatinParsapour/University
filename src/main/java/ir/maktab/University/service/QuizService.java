package ir.maktab.University.service;

import ir.maktab.University.entities.Questions;
import ir.maktab.University.entities.Quiz;

import java.text.ParseException;

public interface QuizService extends BaseService<Quiz, Long> {


    /**
     * Get all of data from controller create new quiz and save to data base
     * @param title the title of quiz
     * @param description description of quiz
     * @param quizTime time of quiz
     * @param fromTime beginning time of quiz
     * @param toTime finish time of quiz
     * @param inDate date of quiz
     * @param courseId id of course quiz belong to
     * @return the quiz saved in data base
     * @throws ParseException the exception for convert string to local time
     */
    Quiz createQuiz(String title, String description, double quizTime, String fromTime, String toTime, String inDate, long courseId) throws ParseException;

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
