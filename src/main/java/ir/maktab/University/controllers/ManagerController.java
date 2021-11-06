package ir.maktab.University.controllers;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.Teacher;
import ir.maktab.University.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {


    private final ManagerService managerService;


    private final CourseController courseController;


    private final TeacherController teacherController;


    private final StudentController studentController;

    @Autowired
    public ManagerController(ManagerService managerService, CourseController courseController, TeacherController teacherController, StudentController studentController) {
        this.managerService = managerService;
        this.courseController = courseController;
        this.teacherController = teacherController;
        this.studentController = studentController;
    }

    @GetMapping("/manager-main")
    public String managerMain(Model model) {
        List<Course> courseList = courseController.courses();
        List<Student> studentList = studentController.students();
        List<Teacher> teacherList = teacherController.teachers();
        model.addAttribute("courses", courseList);
        model.addAttribute("students", studentList);
        model.addAttribute("teachers", teacherList);
        return "Manager";
    }
}
