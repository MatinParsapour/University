package ir.maktab.University.controllers;

import ir.maktab.University.entities.QuestionStatus;
import ir.maktab.University.service.QuestionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question-status")
public class QuestionStatusController {

    private final QuestionStatusService questionStatusService;

    @Autowired
    public QuestionStatusController(QuestionStatusService questionStatusService) {
        this.questionStatusService = questionStatusService;
    }

    @PostMapping("/find-question-status")
    public String findQuestionStatus( long studentResultId, Model model){
        QuestionStatus questionStatus = questionStatusService.getQuestionStatusByStudentResult(studentResultId);
        model.addAttribute("questionStatus",questionStatus);
        return "QuestionAnswers";
    }
}

