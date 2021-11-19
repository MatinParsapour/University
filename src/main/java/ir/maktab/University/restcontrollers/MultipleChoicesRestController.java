package ir.maktab.University.restcontrollers;

import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.service.MultipleChoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiple-choices-rest")
public class MultipleChoicesRestController {

    private final MultipleChoicesService multipleChoicesService;

    @Autowired
    public MultipleChoicesRestController(MultipleChoicesService multipleChoicesService) {
        this.multipleChoicesService = multipleChoicesService;
    }

    /**
     * Get all the data from front for multiple choices and save to data base
     * @param multipleChoices the multiple choices with all changes
     * @param grade the grade of question
     *
     */
    @PostMapping("/update-question")
    public void updateQuestion(MultipleChoices multipleChoices, double grade){
        multipleChoicesService.editQuestion(multipleChoices);
    }
}
