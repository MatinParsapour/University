package ir.maktab.University.service.impl;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionStatus;
import ir.maktab.University.repository.QuestionStatusRepository;
import ir.maktab.University.service.QuestionStatusService;
import ir.maktab.University.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionStatusServiceImpl extends BaseServiceImpl<QuestionStatus,Long, QuestionStatusRepository>
        implements QuestionStatusService {

    private final QuestionStatusRepository questionStatusRepository;

    @Autowired
    public QuestionStatusServiceImpl(QuestionStatusRepository questionStatusRepository) {
        super(questionStatusRepository);
        this.questionStatusRepository = questionStatusRepository;

    }

    @Override
    public QuestionStatus createNewQuestionStatus(QuestionHeader questionHeader, double grade) {
        QuestionStatus questionStatus = new QuestionStatus();
        questionStatus.setQuestionHeader(questionHeader);
        questionStatus.setQuestionPoint(grade);
        return save(questionStatus);
    }
}
