package ir.maktab.University.controllers;

import ir.maktab.University.entities.Quiz;
import ir.maktab.University.service.CourseService;
import ir.maktab.University.service.QuizService;
import ir.maktab.University.service.StudentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    private final StudentResultService studentResultService;

    private final CourseService courseService;

    @Autowired
    public QuizController(QuizService quizService, StudentResultService studentResultService, CourseService courseService) {
        this.quizService = quizService;
        this.studentResultService = studentResultService;
        this.courseService = courseService;
    }

    /**
     * Delete a quiz that teacher chose
     * @param quizId the id of quiz
     * @return a String to redirect to main page for teacher
     */
    @PostMapping("/delete-quiz")
    public String deleteQuiz(long quizId) {
        quizService.deleteById(quizId);
        return "redirect:/teacher/main";
    }

    /**
     * Get id of quiz to edit the information of it
     * @param quizId id of the quiz that teacher chose
     * @param model set the quiz in a model
     * @return a String then go to the edit page
     */
    @PostMapping("/edit-quiz")
    public String editQuiz(long quizId, Model model) {
        Quiz quiz = quizService.findById(quizId).get();
        model.addAttribute("quiz", quiz);
        return "EditQuiz";
    }

    /**
     * A method to get quiz and send to front
     * @param idOfQuiz id of quiz to find the quiz
     * @param model set quiz in model and send to front
     * @return a String then go to the front
     */
    @PostMapping("/take-the-test")
    public String takeTheTest(Long idOfQuiz,Model model){
        String quizDate = quizService.checkDate(idOfQuiz);
        if(quizDate == null){
            String quizBeginningTime = quizService.checkBeginningTime(idOfQuiz);
            if(quizBeginningTime == null){
                String quizFinishDate = quizService.checkFinishTime(idOfQuiz);
                if(quizFinishDate == null){
                    String studentAllow = studentResultService.checkStudent();
                    if(studentAllow == null){
                        Quiz quiz = quizService.findById(idOfQuiz).get();
                        model.addAttribute("quiz",quiz);
                        return "ExamSession";
                    }else{
                        model.addAttribute("message",studentAllow);
                        return "QuizWarningPage";
                    }
                }else {
                    model.addAttribute("message",quizFinishDate);
                    return "QuizWarningPage";
                }
            }else {
                model.addAttribute("message",quizBeginningTime);
                return "QuizWarningPage";
            }
        }else {
            model.addAttribute("message",quizDate);
            return "QuizWarningPage";
        }
    }

    /**
     *
     * @param pageNo
     * @param model
     * @return
     */
    @PostMapping("/exam-details/{pageNo}")
    public String examDetails(@PathVariable(value = "pageNo") int pageNo,
                              long quizId,Model model){
        int pageSize = 1;

        Page<Quiz> page = quizService.findPaginated(pageNo, pageSize, quizId);
        List<Quiz> quizList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("questions", quizList.get(0).getQuestions());
        return "ExamResult";
    }
}
