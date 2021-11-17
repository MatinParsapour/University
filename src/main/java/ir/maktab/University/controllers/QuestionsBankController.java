package ir.maktab.University.controllers;

import ir.maktab.University.entities.QuestionsBank;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.QuestionsBankService;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question-bank")
public class QuestionsBankController {

    private final QuestionsBankService questionsBankService;

    private final QuizService quizService;

    @Autowired
    public QuestionsBankController(QuestionsBankService questionsBankService, QuizService quizService) {
        this.questionsBankService = questionsBankService;
        this.quizService = quizService;
    }

    /**
     * Get quiz id and set find all the questions in questions bank that has the
     * Same course as quiz and send all questions to view for teacher to select
     * A Question and add to quiz
     * @param quizId id of the quiz
     * @param model save quiz and list of questions in model
     * @return a String then return to the view for teacher to pick a question
     */
    @PostMapping("/select-question")
    public String questionsBankPage(long quizId, Model model){
        QuestionsBank questionsBankList = questionsBankService.getAllQuestionsBank(quizId);
        Quiz quiz = quizService.findById(quizId).get();
        model.addAttribute("quiz",quiz);
        if(questionsBankList == null){
            model.addAttribute("questions",null);
        }else{
            model.addAttribute("questions",questionsBankList.getQuestionHeaders());
        }
        return "SelectQuestion";
    }
}
