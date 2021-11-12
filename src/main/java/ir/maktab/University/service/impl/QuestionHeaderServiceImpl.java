package ir.maktab.University.service.impl;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.repository.QuestionHeaderRepository;
import ir.maktab.University.service.QuestionHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionHeaderServiceImpl extends BaseServiceImpl<QuestionHeader,Long, QuestionHeaderRepository>
        implements QuestionHeaderService {

    private final QuestionHeaderRepository questionHeaderRepository;

    @Autowired
    public QuestionHeaderServiceImpl(QuestionHeaderRepository questionHeaderRepository) {
        super(questionHeaderRepository);
        this.questionHeaderRepository = questionHeaderRepository;
    }
}
