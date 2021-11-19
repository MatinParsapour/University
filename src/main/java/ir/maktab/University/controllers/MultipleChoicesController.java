package ir.maktab.University.controllers;

import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.MultipleChoicesService;
import ir.maktab.University.service.QuizService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/multiple-choices")
public class MultipleChoicesController {

    private final MultipleChoicesService multipleChoicesService;

    private final QuizService quizService;

    @Autowired
    public MultipleChoicesController(MultipleChoicesService multipleChoicesService, QuizService quizService) {
        this.multipleChoicesService = multipleChoicesService;
        this.quizService = quizService;
    }

    /**
     * Send information like the quiz teacher wants to add question
     * And new object of multiple choices to the template for teacher to
     * Fill the form
     * @param quizId id of the quiz that teacher wants to add question
     * @param model new model to add quiz and object of multiple choices for teacher to fill the form
     * @return a String then go to the page to display form for teacher
     */
    @PostMapping("/create-new-multiple-choices")
    public String createNewMultipleChoices(long quizId, Model model){
        Quiz quiz = quizService.findById(quizId).get();
        model.addAttribute("quiz",quiz);
        model.addAttribute("multipleChoices", new MultipleChoices());
        return "MultipleChoices";
    }

    /**
     * After teacher filled the form information will come here to create new multiple choices question
     * @param quizId id of the quiz that question belong to
     * @param multipleChoices an object of descriptive includes : title, header(problem), correct answer
     * @param grade point of the question
     * @param option the options that teacher entered
     * @param correctAnswer the correct answer for this question teacher selected
     * @return a String to return to main page for teacher
     */
    @PostMapping("/add-multiple-choices")
    public String addMultipleChoices(long quizId, MultipleChoices multipleChoices, double grade, String option, String correctAnswer){
        Security.setQuiz(quizService.findById(quizId).get());
        multipleChoicesService.createNewMultipleChoices(multipleChoices,grade,option,correctAnswer);
        return "redirect:/teacher/main";
    }

    /**
     * Get quiz id and question id and grade to find the question
     * And find quiz and set question to quiz plus point of the
     * question
     * @param quizId id of quiz that question add to
     * @param questionId id of question that add to quiz
     * @param grade point of question
     * @return a String then redirect to main page of teacher
     */
    @PostMapping("/add-to-quiz")
    public String addQuestionToQuiz(long quizId, long questionId, double grade){
        Security.setQuiz(quizService.findById(quizId).get());
        multipleChoicesService.setDescriptiveQuestion(questionId, grade);
        return "redirect:/teacher/main";
    }
}
