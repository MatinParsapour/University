package ir.maktab.University.service.impl;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionsBank;
import ir.maktab.University.repository.QuestionsBankRepository;
import ir.maktab.University.service.QuestionsBankService;
import ir.maktab.University.service.TeacherService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsBankServiceImpl extends BaseServiceImpl<QuestionsBank,Long, QuestionsBankRepository>
        implements QuestionsBankService {

    private final QuestionsBankRepository questionsBankRepository;

    private final TeacherService teacherService;

    @Autowired
    public QuestionsBankServiceImpl(QuestionsBankRepository questionsBankRepository, QuestionsBankRepository questionsBankRepository1, TeacherService teacherService) {
        super(questionsBankRepository);
        this.questionsBankRepository = questionsBankRepository1;
        this.teacherService = teacherService;
    }

    @Override
    public void addQuestionHeaderToQuestionBank(QuestionHeader questionHeader) {
        if(checkTeacherQuestionsBank()){
            QuestionsBank questionsBank = Security.getTeacher().getQuestionsBank();
            questionsBank.getQuestionHeaders().add(questionHeader);
            questionsBank.getCourses().add(Security.getQuiz().getCourse());
            save(questionsBank);
        }else{
            QuestionsBank questionsBank = new QuestionsBank();
            questionsBank.getQuestionHeaders().add(questionHeader);
            questionsBank.getCourses().add(Security.getQuiz().getCourse());
            QuestionsBank savedQuestionsBank = save(questionsBank);
            teacherService.addQuestionsBankToTeacher(savedQuestionsBank);
        }
    }

    @Override
    public boolean checkTeacherQuestionsBank() {
        return Security.getTeacher().getQuestionsBank() != null;
    }

    @Override
    public boolean checkQuestionsBankCourses(QuestionsBank questionsBank) {
        for(Course course : questionsBank.getCourses()){
            if(course.getId().equals(Security.getQuiz().getCourse().getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public QuestionsBank getAllQuestionsBank(long quizId) {
        Quiz quiz = quizService.findById(quizId).get();
        Course course = courseService.getCourseByQuiz(quiz);
        return questionsBankRepository.findAllByCourses(course);
    }
}
