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

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    public Quiz createQuiz(String title, String description, double quizTime, String fromTime, String toTime, String inDate, long courseId) throws ParseException {
        fromTime = fromTime.replace("00:","12:");
        toTime = toTime.replace("00:","12:");
        LocalTime fromTimeDate = LocalTime.parse(fromTime,DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime toTimeDate = LocalTime.parse(toTime,DateTimeFormatter.ofPattern("HH:mm"));
        LocalDate examDate = LocalDate.parse(inDate);
        Course course = courseService.findById(courseId).get();
        Quiz quiz = new Quiz();
        quiz.setCourse(course);
        quiz.setTitle(title);
        quiz.setDescription(description);
        quiz.setQuizTime(quizTime);
        quiz.setFromTime(fromTimeDate);
        quiz.setToTime(toTimeDate);
        quiz.setInDate(examDate);
        quiz.setIsActive(true);
        Quiz createdQuiz = save(quiz);
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
        quiz.getQuestions().add(questions);
        save(quiz);
    }

    @Override
    public String checkBeginningTime(long idOfQuiz) {
        Quiz quiz = findById(idOfQuiz).get();
        if(LocalTime.now().isBefore(quiz.getFromTime())){
            return "زمان آزمون هنوز نرسیده";
        }
        return null;
    }

    @Override
    public String checkFinishTime(long idOfQuiz) {
        Quiz quiz = findById(idOfQuiz).get();
        if(LocalTime.now().isAfter(quiz.getToTime())){
            return "زمان آزمون تمام شده";
        }
        return null;
    }

    @Override
    public String checkDate(long idOfQuiz) {
        Quiz quiz = findById(idOfQuiz).get();
        if(LocalDate.now().isAfter(quiz.getInDate())){
            return "امروز تاریخ آزمون نیست";
        }
        return null;
    }
}
