package ir.maktab.University.service.impl;

import ir.maktab.University.entities.QuestionAnswer;
import ir.maktab.University.repository.QuestionAnswerRepository;
import ir.maktab.University.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerServiceImpl extends BaseServiceImpl<QuestionAnswer,Long, QuestionAnswerRepository>
        implements QuestionAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    public QuestionAnswerServiceImpl(QuestionAnswerRepository questionAnswerRepository) {
        super(questionAnswerRepository);
        this.questionAnswerRepository = questionAnswerRepository;
    }
}
