package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Quiz;
import ir.maktab.University.repository.QuizRepository;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl extends BaseServiceImpl<Quiz,Long, QuizRepository>
        implements QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        super(quizRepository);
        this.quizRepository = quizRepository;
    }
}
