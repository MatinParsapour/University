package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Question;
import ir.maktab.University.repository.QuestionRepository;
import ir.maktab.University.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question,Long, QuestionRepository>
        implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        super(questionRepository);
        this.questionRepository = questionRepository;
    }
}
