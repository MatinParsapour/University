package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Grade;
import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.Questions;
import ir.maktab.University.repository.QuestionsRepository;
import ir.maktab.University.service.GradeService;
import ir.maktab.University.service.QuestionsService;
import ir.maktab.University.service.QuizService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestionsServiceImpl extends BaseServiceImpl<Questions,Long, QuestionsRepository>
        implements QuestionsService {

    private final QuestionsRepository questionsRepository;

    private final GradeService gradeService;

    private final QuizService quizService;

    @Autowired
    public QuestionsServiceImpl(QuestionsRepository questionsRepository, GradeService gradeService, QuizService quizService) {
        super(questionsRepository);
        this.questionsRepository = questionsRepository;
        this.gradeService = gradeService;
        this.quizService = quizService;
    }

    @Override
    public void addToQuestions(QuestionHeader questionHeader, double grade) {
        Questions questions = new Questions();
        questions.setQuestionHeader(questionHeader);
        Grade newGrade = gradeService.createNewGrade(grade);
        questions.setGrade(newGrade);
        Questions savedQuestions = save(questions);
        quizService.addQuestions(savedQuestions);
    }
}
