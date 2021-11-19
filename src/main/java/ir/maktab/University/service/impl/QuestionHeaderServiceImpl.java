package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.repository.QuestionHeaderRepository;
import ir.maktab.University.service.QuestionHeaderService;
import ir.maktab.University.service.QuestionsBankService;
import ir.maktab.University.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionHeaderServiceImpl extends BaseServiceImpl<QuestionHeader,Long, QuestionHeaderRepository>
        implements QuestionHeaderService {

    private final QuestionHeaderRepository questionHeaderRepository;

    private final QuestionsService questionsService;

    private final QuestionsBankService questionsBankService;

    @Autowired
    public QuestionHeaderServiceImpl(QuestionHeaderRepository questionHeaderRepository, QuestionsService questionsService, QuestionsBankService questionsBankService) {
        super(questionHeaderRepository);
        this.questionHeaderRepository = questionHeaderRepository;
        this.questionsService = questionsService;
        this.questionsBankService = questionsBankService;
    }

    @Override
    public void createNewQuestionHeaderByDescriptive(Descriptive descriptive,double grade) {
        QuestionHeader questionHeader = new QuestionHeader();
        questionHeader.setDescriptive(descriptive);
        QuestionHeader savedQuestionHeader = save(questionHeader);
        questionsBankService.addQuestionHeaderToQuestionBank(savedQuestionHeader);
        questionsService.addToQuestions(questionHeader,grade);
    }

    @Override
    public void createNewQuestionHeaderByMultipleChoices(MultipleChoices multipleChoices, double grade) {
        QuestionHeader questionHeader = new QuestionHeader();
        questionHeader.setMultipleChoices(multipleChoices);
        QuestionHeader savedQuestionHeader = save(questionHeader);
        questionsBankService.addQuestionHeaderToQuestionBank(savedQuestionHeader);
        questionsService.addToQuestions(questionHeader,grade);
    }

    @Override
    public void addNewDescriptiveQuestion(Descriptive descriptive, double grade) {
        QuestionHeader questionHeader = questionHeaderRepository.findByDescriptive(descriptive);
        questionsService.addToQuestions(questionHeader,grade);
    }

    @Override
    public void addNewMultipleChoicesQuestion(MultipleChoices multipleChoices, double grade) {
        QuestionHeader questionHeader = questionHeaderRepository.findByMultipleChoices(multipleChoices);
        questionsService.addToQuestions(questionHeader,grade);
    }
}
