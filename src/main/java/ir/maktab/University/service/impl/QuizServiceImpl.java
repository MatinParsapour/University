package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Questions;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.repository.QuizRepository;
import ir.maktab.University.service.CourseService;
import ir.maktab.University.service.QuizService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuizServiceImpl extends BaseServiceImpl<Quiz,Long, QuizRepository>
        implements QuizService {

    private final QuizRepository quizRepository;

    private final CourseService courseService;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository,
                           CourseService courseService) {
        super(quizRepository);
        this.quizRepository = quizRepository;
        this.courseService = courseService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Quiz createQuiz(Quiz quiz, long courseId) {
        Course course = courseService.findById(courseId).get();
        quiz.setIsActive(true);
        quiz.setCourse(course);
        Quiz createdQuiz = save(quiz);
        courseService.addQuizToCourse(course,createdQuiz);
        return createdQuiz;
    }

    @Override
    public void editQuiz(long quizId, String title, String description, Double quizTime) {
        Quiz quiz = findById(quizId).get();
        quiz.setTitle(title);
        quiz.setDescription(description);
        quiz.setQuizTime(quizTime);
        save(quiz);
    }

    @Override
    public void addQuestions(Questions questions) {
        Quiz quiz = Security.getQuiz();
        quiz.setQuestions(questions);
        save(quiz);
    }
}
