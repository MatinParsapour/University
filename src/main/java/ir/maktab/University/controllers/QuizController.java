package ir.maktab.University.controllers;

import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.CourseService;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    private final CourseService courseService;

    @Autowired
    public QuizController(QuizService quizService, CourseService courseService) {
        this.quizService = quizService;
        this.courseService = courseService;
    }

    /**
     * Set two way relationship between quiz and course
     *
     * @param quiz     the quiz that teacher created
     * @param courseId id of course that quiz relate to
     * @return a String to redirect to main page for teacher
     */
    @PostMapping("/create-quiz")
    public String createQuiz(Quiz quiz, long courseId) {
        quizService.createQuiz(quiz, courseId);
        return "redirect:/teacher/main";
    }

    /**
     * Get the information about the quiz that teacher may changed information of it
     * @param quizId the id of quiz that teacher chose
     * @param title the title that may changed
     * @param description the description that may changed
     * @param quizTime the quiz time that may changed
     * @return a String to redirect to main page of teacher
     */
    @PostMapping("/change-quiz-properties")
    public String changeQuizProperties(long quizId, String title, String description, Double quizTime) {
        quizService.editQuiz(quizId, title, description, quizTime);
        return "redirect:/teacher/main";
    }

    /**
     * Delete a quiz that teacher chose
     * @param quizId the id of quiz
     * @return a String to redirect to main page for teacher
     */
    @PostMapping("/delete-quiz")
    public String deleteQuiz(long quizId) {
        quizService.deleteById(quizId);
        return "redirect:/teacher/main";
    }

    /**
     * Get id of quiz to edit the information of it
     * @param quizId id of the quiz that teacher chose
     * @param model set the quiz in a model
     * @return a String then go to the edit page
     */
    @PostMapping("/edit-quiz")
    public String editQuiz(long quizId, Model model) {
        Quiz quiz = quizService.findById(quizId).get();
        model.addAttribute("quiz", quiz);
        return "EditQuiz";
    }
}
