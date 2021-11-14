package ir.maktab.University.service.impl;

import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.entities.Options;
import ir.maktab.University.repository.MultipleChoicesRepository;
import ir.maktab.University.service.MultipleChoicesService;
import ir.maktab.University.service.QuestionHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MultipleChoicesServiceImpl extends BaseServiceImpl<MultipleChoices,Long, MultipleChoicesRepository>
        implements MultipleChoicesService {

    private final MultipleChoicesRepository multipleChoicesRepository;

    private final QuestionHeaderService questionHeaderService;

    @Autowired
    public MultipleChoicesServiceImpl(MultipleChoicesRepository multipleChoicesRepository, QuestionHeaderService questionHeaderService) {
        super(multipleChoicesRepository);
        this.multipleChoicesRepository = multipleChoicesRepository;
        this.questionHeaderService = questionHeaderService;
    }

    @Override
    public void createNewMultipleChoices(MultipleChoices multipleChoices, double grade, String option) {
        List<String> options = Arrays.asList(option.split(","));
        for(String questionOptions : options){
            Options newOptions = new Options();
            newOptions.setOptions(questionOptions);
            multipleChoices.getOptions().add(newOptions);
        }
        MultipleChoices savedMultipleChoices = save(multipleChoices);
        questionHeaderService.createNewQuestionHeaderByMultipleChoices(savedMultipleChoices,grade);
    }

    @Override
    public void editQuestion(long questionId, String title, String header, String options) {
        MultipleChoices multipleChoices = findById(questionId).get();
        List<String> option = Arrays.asList(options.split(","));
        for(String questionOptions : option){
            Options newOptions = new Options();
            newOptions.setOptions(questionOptions);
            multipleChoices.getOptions().add(newOptions);
        }
        save(multipleChoices);
    }

    @Override
    public void setDescriptiveQuestion(long questionId, double grade) {
        questionHeaderService.addNewMultipleChoicesQuestion(findById(questionId).get(), grade);
    }
}
