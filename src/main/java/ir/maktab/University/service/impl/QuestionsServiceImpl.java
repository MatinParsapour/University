package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Grade;
import ir.maktab.University.entities.Question;
import ir.maktab.University.entities.Questions;
import ir.maktab.University.repository.QuestionsRepository;
import ir.maktab.University.service.GradeService;
import ir.maktab.University.service.QuestionService;
import ir.maktab.University.service.QuestionsService;
import ir.maktab.University.service.QuizService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addToQuestions(Question question,double grade) {
        Grade newGrade = gradeService.createNewGrade(grade);
        if(checkQuestions()){
            Questions questions = Security.getQuiz().getQuestions();
            questions.getQuestionList().add(question);
            questions.getGradeList().add(newGrade);
            Questions savedQuestions = save(questions);
        }else{
            Questions questions = new Questions();
            questions.getQuestionList().add(question);
            questions.getGradeList().add(newGrade);
            Questions savedQuestions = save(questions);
            quizService.addQuestions(savedQuestions);
        }
    }

    @Override
    public boolean checkQuestions() {
        return Security.getQuiz().getQuestions() != null;
    }
}
