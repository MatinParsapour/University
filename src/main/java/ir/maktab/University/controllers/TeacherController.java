package ir.maktab.University.controllers;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Role;
import ir.maktab.University.entities.Teacher;
import ir.maktab.University.entities.User;
import ir.maktab.University.service.TeacherService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {


    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * Get all the courses that teacher teach in them
     * @return a model and view that contains all the courses and address of the template
     */
    @GetMapping("/main")
    public ModelAndView teacherMain(){
        List<Course> courses = Security.getTeacher().getCourse();
        return new ModelAndView("Teacher","courses",courses);
    }

    /**
     * return a true if the user with username is accepted by manager
     * @param username the username that searched for
     * @return true if student accepted
     */
    @PostMapping("/teacher-allow")
    public Boolean teacherAllow(String username) {
        return teacherService.isTeacherAllow(username);
    }
    /**
     * Find all teachers
     * @return a list of teachers
     */
    @GetMapping("/get-teachers")
    public List<Teacher> teachers() {
        return teacherService.getAllTeachers();
    }

    /**
     * Find teacher by id
     * @param id id of teacher
     * @return null if no matches found
     */
    @PostMapping("/get-teacher")
    public Teacher getTeacher(long id) {
        return teacherService.findById(id).get();
    }

    /**
     * Create a new teacher based on the form that user filled in sign up form
     * @param user the information about user in sign up form
     * @return a student that saved in data base
     */
    public Teacher addTeacher(User user) {
        return teacherService.createTeacher(user);
    }

    /**
     * Find the teacher and reject the teacher
     * @param userId id of user that manager chose
     */
    @PostMapping("/reject-teacher")
    public void rejectTeacher(long userId) {
        teacherService.rejectTeacher(userId);
    }

    /**
     * Find the teacher and accept the teacher
     * @param userId id of user that manager chose
     */
    @PostMapping("/accept-teacher")
    public void acceptTeacher(long userId) {
        teacherService.acceptTeacher(userId);
    }

    /**
     * Get all the information of previous user and add it to new teacher
     * @param user the information of user
     * @param username the username of user
     */
    @PostMapping("/change-to-teacher")
    public void changeToTeacher(User user,String username) {
        teacherService.changeRoleToTeacher(user,username);
    }

    /**
     * Find the teacher that manager is looking for and in active the teacher
     * @param userId id of the user
     */
    @PostMapping("/delete-teacher")
    public void inActiveTeacher(long userId) {
        teacherService.inActiveTeacher(userId);
    }
}
