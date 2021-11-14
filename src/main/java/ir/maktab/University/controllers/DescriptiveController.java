package ir.maktab.University.controllers;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.DescriptiveService;
import ir.maktab.University.service.QuizService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/descriptive")
public class DescriptiveController {

    private final DescriptiveService descriptiveService;

    private final QuizService quizService;

    @Autowired
    public DescriptiveController(DescriptiveService descriptiveService, QuizService quizService) {
        this.descriptiveService = descriptiveService;
        this.quizService = quizService;
    }

    /**
     * Send information like the quiz teacher wants to add question
     * And new object of descriptive to the template for teacher to
     * Fill the form
     * @param quizId id of the quiz that teacher wants to add question
     * @param model new model to add quiz and object of descriptive for teacher to fill the form
     * @return a String then go to the page to display form for teacher
     */
    @PostMapping("/create-new-descriptive")
    public String createNewDescriptive(long quizId, Model model){
        Quiz quiz = quizService.findById(quizId).get();
        model.addAttribute("quiz",quiz);
        model.addAttribute("descriptive", new Descriptive());
        return "DescriptiveQuestion";
    }

    /**
     * After teacher filled the form information will come here to create new descriptive question
     * @param quizId id of the quiz that question belong to
     * @param descriptive an object of descriptive includes : title, header(problem), correct answer
     * @param grade point of the question
     * @return a String to return to main page for teacher
     */
    @PostMapping("/add-descriptive")
    public String addDescriptive(long quizId, Descriptive descriptive, double grade){
        Security.setQuiz(quizService.findById(quizId).get());
        descriptiveService.createNewDescriptive(descriptive,grade);
        return "redirect:/teacher/main";
    }

    /**
     * Get all the data from front and set it to question and save to data base
     * @param questionId id of the descriptive question
     * @param title the title that may changed
     * @param header the problem that may changed
     * @param correctAnswer the correct answer that may changed
     * @return a String then redirect to main page for teacher
     */
    @PostMapping("/edit-question")
    public String editQuestion(long questionId, String title, String header, String correctAnswer){
        descriptiveService.editQuestion(questionId,title,header,correctAnswer);
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
        descriptiveService.setDescriptiveQuestion(questionId,grade);
        return "redirect:/teacher/main";
    }
}
