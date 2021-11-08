package ir.maktab.University.controllers;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.CourseService;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    private final CourseService courseService;

    @Autowired
    public QuizController(QuizService quizService, CourseService courseService) {
        this.quizService = quizService;
        this.courseService = courseService;
    }

    @PostMapping("/create-quiz")
    public String createQuiz(Quiz quiz, String minutes, String seconds, long courseId){
        Double quizTime = Double.parseDouble(minutes + "." + seconds);
        quiz.setQuizTime(quizTime);
        quiz.setIsActive(true);
        Course course = courseService.findById(courseId).get();
        quiz.setCourse(course);
        Quiz createdQuiz = quizService.save(quiz);
        course.getQuizzes().add(createdQuiz);
        courseService.save(course);
        return "redirect:/teacher/main";
    }

    @PostMapping("/delete-quiz")
    public String deleteQuiz(long quizId){
        quizService.deleteById(quizId);
        return "redirect:/teacher/main";
    }
}
