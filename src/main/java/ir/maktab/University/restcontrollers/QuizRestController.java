package ir.maktab.University.restcontrollers;

import ir.maktab.University.entities.Questions;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.entities.dto.extra.QuestionHeaderDTO;
import ir.maktab.University.service.QuestionsService;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Set;

@RestController
@RequestMapping("/question-rest")
public class QuizRestController {

    private final QuizService quizService;

    private final QuestionsService questionsService;

    @Autowired
    public QuizRestController(QuizService quizService, QuestionsService questionsService) {
        this.quizService = quizService;
        this.questionsService = questionsService;
    }

    /**
     * Get all the data for quiz from front and save to data base
     * @param quizId the id of quiz
     * @param title the title may have changed
     * @param description the description may have changed
     * @param quizTime the quiz time may have changed
     */
    @PostMapping("/update-question")
    public void updateQuestion(long quizId, String title, String description, Double quizTime) {
        quizService.editQuiz(quizId,title,description,quizTime);
    }

    /**
     * Send a set of dto of all questions to front
     * @param idOfQuiz the id of exam that is going to take
     * @return set of dto of questions
     */
    @PostMapping("/get-all-quiz-questions")
    public Set<QuestionHeaderDTO> getAllQuizQuestions(long idOfQuiz){
        Quiz quiz = quizService.findById(idOfQuiz).get();
        return questionsService.questionHeaders(quiz.getQuestions());
    }

    /**
     * Get all of data from front and send to service
     * @param title the title of quiz
     * @param description description of quiz
     * @param quizTime time of quiz
     * @param fromTime beginning time of quiz
     * @param toTime finish time of quiz
     * @param inDate date of quiz
     * @param courseId id of course quiz belong to
     * @throws ParseException and exception to convert string to local time
     */
    @PostMapping("/create-quiz")
    public void createQuiz(String title, String description, double quizTime, String fromTime, String toTime, String inDate, long courseId) throws ParseException {
        quizService.createQuiz(title,description,quizTime,fromTime,toTime,inDate,courseId);
    }
}
