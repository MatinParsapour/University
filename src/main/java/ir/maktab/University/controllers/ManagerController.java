package ir.maktab.University.controllers;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.Teacher;
import ir.maktab.University.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CourseController courseController;

    @Autowired
    private TeacherController teacherController;

    @Autowired
    private StudentController studentController;

    @GetMapping("/manager-main")
    public String managerMain(Model model){
        List<Course> courseList = courseController.courses();
        List<Student> studentList = studentController.students();
        List<Teacher> teacherList = teacherController.teachers();
        model.addAttribute("courses",courseList);
        model.addAttribute("students", studentList);
        model.addAttribute("teachers",teacherList);
        return "Manager";
    }
}
