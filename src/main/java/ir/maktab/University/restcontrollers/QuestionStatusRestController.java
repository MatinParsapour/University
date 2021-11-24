package ir.maktab.University.restcontrollers;

import com.google.common.base.Splitter;
import ir.maktab.University.entities.dto.extra.StudentAnswersDTO;
import ir.maktab.University.service.QuestionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/question-status-rest")
public class QuestionStatusRestController {

    private final QuestionStatusService questionStatusService;

    @Autowired
    public QuestionStatusRestController(QuestionStatusService questionStatusService) {
        this.questionStatusService = questionStatusService;
    }

    /**
     * Get all student answers and save to data base
     * @param studentAnswers all the student answers
     */
    @PostMapping("/questions-result")
    public void questionsResult(@RequestBody List<StudentAnswersDTO> studentAnswers) {
        questionStatusService.setResult(studentAnswers);
    }
}
