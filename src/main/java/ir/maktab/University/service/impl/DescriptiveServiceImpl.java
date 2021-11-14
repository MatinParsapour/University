package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.repository.DescriptiveRepository;
import ir.maktab.University.service.DescriptiveService;
import ir.maktab.University.service.QuestionHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DescriptiveServiceImpl extends BaseServiceImpl<Descriptive,Long, DescriptiveRepository>
        implements DescriptiveService {

    private final DescriptiveRepository descriptiveRepository;

    private final QuestionHeaderService questionHeaderService;

    @Autowired
    public DescriptiveServiceImpl(DescriptiveRepository descriptiveRepository, QuestionHeaderService questionHeaderService) {
        super(descriptiveRepository);
        this.descriptiveRepository = descriptiveRepository;
        this.questionHeaderService = questionHeaderService;
    }

    @Override
    public void createNewDescriptive(Descriptive descriptive, double grade) {
        Descriptive savedDescriptive = save(descriptive);
        questionHeaderService.createNewQuestionHeaderByDescriptive(savedDescriptive,grade);
    }

    @Override
    public void editQuestion(long questionId, String title, String header, String correctAnswer) {
        Descriptive descriptive = findById(questionId).get();
        descriptive.setTitle(title);
        descriptive.setCorrectAnswer(correctAnswer);
        descriptive.setHeader(header);
        save(descriptive);
    }

    @Override
    public void setDescriptiveQuestion(long questionId, double grade) {
        questionHeaderService.addNewDescriptiveQuestion(findById(questionId).get(),grade);
    }
}
