package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Question;
import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.repository.QuestionRepository;
import ir.maktab.University.service.QuestionService;
import ir.maktab.University.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question,Long, QuestionRepository>
        implements QuestionService {

    private final QuestionRepository questionRepository;

    private final QuestionsService questionsService;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionsService questionsService) {
        super(questionRepository);
        this.questionRepository = questionRepository;
        this.questionsService = questionsService;
    }

    @Override
    public void createNewQuestion(QuestionHeader questionHeader,double grade) {
        Question question = new Question();
        question.setQuestionHeader(questionHeader);
        Question savedQuestion = save(question);
        questionsService.addToQuestions(savedQuestion,grade);
    }
}
