package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionsBank;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.repository.QuestionsBankRepository;
import ir.maktab.University.service.CourseService;
import ir.maktab.University.service.QuestionsBankService;
import ir.maktab.University.service.QuizService;
import ir.maktab.University.service.TeacherService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class QuestionsBankServiceImpl extends BaseServiceImpl<QuestionsBank,Long, QuestionsBankRepository>
        implements QuestionsBankService {

    private final QuestionsBankRepository questionsBankRepository;

    private final TeacherService teacherService;

    private final CourseService courseService;

    private final QuizService quizService;

    @Autowired
    public QuestionsBankServiceImpl(QuestionsBankRepository questionsBankRepository, QuestionsBankRepository questionsBankRepository1, TeacherService teacherService, CourseService courseService, QuizService quizService) {
        super(questionsBankRepository);
        this.questionsBankRepository = questionsBankRepository1;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.quizService = quizService;
    }

    @Override
    public void addQuestionHeaderToQuestionBank(QuestionHeader questionHeader) {
        if(checkTeacherQuestionsBank()){
            QuestionsBank questionsBank = Security.getTeacher().getQuestionsBank();
            Set<Course> courses = questionsBank.getCourses();
            Set<QuestionHeader> questionHeaders = questionsBank.getQuestionHeaders();
            QuestionsBank newQuestionsBank = new QuestionsBank();
            newQuestionsBank.getQuestionHeaders().addAll(questionHeaders);
            newQuestionsBank.getCourses().addAll(courses);
            questionsBank.getCourses().removeAll(courses);
            questionsBank.getQuestionHeaders().removeAll(questionHeaders);
            save(questionsBank);
            if(!checkQuestionsBankCourses(newQuestionsBank)){
                newQuestionsBank.getQuestionHeaders().add(questionHeader);
                newQuestionsBank.getCourses().add(Security.getQuiz().getCourse());
                QuestionsBank savedQuestionsBank = save(newQuestionsBank);
                teacherService.addQuestionsBankToTeacher(savedQuestionsBank);
            }else{
                newQuestionsBank.getQuestionHeaders().add(questionHeader);
                QuestionsBank savedQuestionsBank = save(newQuestionsBank);
                teacherService.addQuestionsBankToTeacher(savedQuestionsBank);
            }
            deleteById(questionsBank.getId());
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
