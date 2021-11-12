package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Questions;
import ir.maktab.University.repository.QuestionsRepository;
import ir.maktab.University.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsServiceImpl extends BaseServiceImpl<Questions,Long, QuestionsRepository>
        implements QuestionsService {

    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionsServiceImpl(QuestionsRepository questionsRepository) {
        super(questionsRepository);
        this.questionsRepository = questionsRepository;
    }
}
