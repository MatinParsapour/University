package ir.maktab.University.restcontrollers;

import ir.maktab.University.service.StudentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-result")
public class StudentResultRest {

    private final StudentResultService studentResultService;

    @Autowired
    public StudentResultRest(StudentResultService studentResultService) {
        this.studentResultService = studentResultService;
    }


    /**
     * Check if student have participated in the exam
     * @return a boolean if haven't it returns false
     */
    @PostMapping("/check-student")
    private boolean checkStudent(){
        return studentResultService.checkStudent();
    }

    /**
     * Get student result id and grade and update student result
     * @param studentResultId the id of student result
     * @param grade the grade teacher chose
     */
    @PostMapping("/change-grade")
    public void changeGrade(long studentResultId, double grade){
        studentResultService.changeStudentResultGrade(studentResultId,grade);
    }
}
