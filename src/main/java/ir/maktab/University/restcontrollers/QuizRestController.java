package ir.maktab.University.restcontrollers;

import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question-rest")
public class QuizRestController {

    private final QuizService quizService;

    @Autowired
    public QuizRestController(QuizService quizService) {
        this.quizService = quizService;
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
}
