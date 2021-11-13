package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.repository.QuestionHeaderRepository;
import ir.maktab.University.service.QuestionHeaderService;
import ir.maktab.University.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionHeaderServiceImpl extends BaseServiceImpl<QuestionHeader,Long, QuestionHeaderRepository>
        implements QuestionHeaderService {

    private final QuestionHeaderRepository questionHeaderRepository;

    private final QuestionService questionService;

    @Autowired
    public QuestionHeaderServiceImpl(QuestionHeaderRepository questionHeaderRepository, QuestionService questionService) {
        super(questionHeaderRepository);
        this.questionHeaderRepository = questionHeaderRepository;
        this.questionService = questionService;
    }

    @Override
    public void createNewQuestionHeaderByDescriptive(Descriptive descriptive,double grade) {
        QuestionHeader questionHeader = new QuestionHeader();
        questionHeader.setDescriptive(descriptive);
        QuestionHeader savedQuestionHeader = save(questionHeader);
        questionService.createNewQuestion(savedQuestionHeader,grade);
    }
}
