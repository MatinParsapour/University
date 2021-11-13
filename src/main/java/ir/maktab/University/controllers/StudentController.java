package ir.maktab.University.controllers;

import ir.maktab.University.entities.Role;
import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.User;
import ir.maktab.University.entities.dto.StudentDTO;
import ir.maktab.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")

public class StudentController {


    private final StudentService studentService;

    private final CourseController courseController;

    @Autowired
    public StudentController(StudentService studentService, CourseController courseController) {
        this.studentService = studentService;
        this.courseController = courseController;
    }

    /**
     * return a true if the user with username is accepted by manager
     * @param username the username that searched for
     * @return true if student accepted
     */
    @PostMapping("/student-allow")
    public Boolean isAllow(String username) {
        return studentService.isStudentAllow(username);
    }

    /**
     * Find all students
     * @return a list of students
     */
    @GetMapping("/get-students")
    public List<StudentDTO> students() {
        return studentService.getAllStudentDTOs();
    }


    /**
     * Create a new student based on the form that user filled in sign up form
     * @param user the information about user in sign up form
     * @return a student that saved in data base
     */
    public Student addStudent(User user) {
        return studentService.createStudent(user);
    }

    /**
     * Find the student and reject the student
     * @param userId id of user that manager chose
     * @return a String to redirect to main page for manager
     */
    @PostMapping("/reject-student")
    public String rejectStudent(long userId) {
        studentService.rejectStudent(userId);
        return "redirect:/manager/manager-main";
    }

    /**
     * Find the student and accept the student
     * @param userId id of user that manager chose
     * @return a String to redirect to main page for manager
     */
    @PostMapping("/accept-student")
    public String acceptStudent(long userId) {
        studentService.acceptStudent(userId);
        return "redirect:/manager/manager-main";
    }

    /**
     * Find the student that manager is looking for and in active the student
     * @param userId id of the user
     */
    @PostMapping("/delete-student")
    public void inActiveStudent(long userId) {
        studentService.inActiveStudent(userId);
    }

    /**
     * Get all the information of previous user and add it to new student
     * @param user the information of user
     * @param username the username of user
     */
    @PostMapping("/change-to-student")
    public void changeToStudent(User user, String username) {
        studentService.changeRoleToStudent(user,username);
    }
}
