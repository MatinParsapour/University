package ir.maktab.University.controllers;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.CourseService;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("/edit-quiz")
    public String editQuiz(long quizId, Model model){
        Quiz quiz = quizService.findById(quizId).get();
        model.addAttribute("quiz",quiz);
        return "EditQuiz";
    }

    @PostMapping("/change-quiz-title")
    public String changeQuizTitle(long quizId, String newTitle){
        Quiz quiz = quizService.findById(quizId).get();
        quiz.setTitle(newTitle);
        quizService.save(quiz);
        return "redirect:/teacher/main";
    }

    @PostMapping("/change-quiz-description")
    public String changeQuizDescription(long quizId, String message){
        Quiz quiz = quizService.findById(quizId).get();
        quiz.setDescription(message);
        quizService.save(quiz);
        return "redirect:/teacher/main";
    }
    @PostMapping("/change-quiz-time")
    public String changeQuizTime(long quizId, String minutes, String seconds){
        Quiz quiz = quizService.findById(quizId).get();
        quiz.setQuizTime(Double.parseDouble(minutes + "." + seconds));
        quizService.save(quiz);
        return "redirect:/teacher/main";
    }
}
