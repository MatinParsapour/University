package ir.maktab.University.restcontrollers;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.DescriptiveService;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/descriptive-rest")
public class DescriptiveRestController {

    private final QuizService quizService;

    private final DescriptiveService descriptiveService;

    @Autowired
    public DescriptiveRestController(QuizService quizService, DescriptiveService descriptiveService) {
        this.quizService = quizService;
        this.descriptiveService = descriptiveService;
    }

    /**
     * Get all the changes from front and save to database
     * @param descriptive the descriptive object with all changes
     * @param grade the grade of question
     */
    @PostMapping("/update-question")
    public void updateQuestion(Descriptive descriptive, double grade){
        descriptiveService.editQuestion(descriptive);
    }

    @PostMapping("/descriptive-page")
    public void updateQuestion(long quizId){
        Quiz quiz = quizService.findById(quizId).get();
        new ModelAndView("DescriptiveQuestion").addObject("quiz",quiz)
                .addObject("descriptive",new Descriptive());
    }
}
