package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.repository.QuestionHeaderRepository;
import ir.maktab.University.service.QuestionHeaderService;
import ir.maktab.University.service.QuestionService;
import ir.maktab.University.service.QuestionsBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionHeaderServiceImpl extends BaseServiceImpl<QuestionHeader,Long, QuestionHeaderRepository>
        implements QuestionHeaderService {

    private final QuestionHeaderRepository questionHeaderRepository;

    private final QuestionService questionService;

    private final QuestionsBankService questionsBankService;

    @Autowired
    public QuestionHeaderServiceImpl(QuestionHeaderRepository questionHeaderRepository, QuestionService questionService, QuestionsBankService questionsBankService) {
        super(questionHeaderRepository);
        this.questionHeaderRepository = questionHeaderRepository;
        this.questionService = questionService;
        this.questionsBankService = questionsBankService;
    }

    @Override
    public void createNewQuestionHeaderByDescriptive(Descriptive descriptive,double grade) {
        QuestionHeader questionHeader = new QuestionHeader();
        questionHeader.setDescriptive(descriptive);
        QuestionHeader savedQuestionHeader = save(questionHeader);
        questionsBankService.addQuestionHeaderToQuestionBank(savedQuestionHeader);
        questionService.createNewQuestion(savedQuestionHeader,grade);
    }

    @Override
    public void createNewQuestionHeaderByMultipleChoices(MultipleChoices multipleChoices, double grade) {
        QuestionHeader questionHeader = new QuestionHeader();
        questionHeader.setMultipleChoices(multipleChoices);
        QuestionHeader savedQuestionHeader = save(questionHeader);
        questionsBankService.addQuestionHeaderToQuestionBank(savedQuestionHeader);
        questionService.createNewQuestion(savedQuestionHeader,grade);
    }
}
