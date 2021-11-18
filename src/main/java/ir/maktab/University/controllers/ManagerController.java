package ir.maktab.University.controllers;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.dto.StudentDTO;
import ir.maktab.University.entities.dto.TeacherDTO;
import ir.maktab.University.restcontrollers.TeacherController;
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

    /**
     * Find all courses and teachers and students for manager
     * @param model set courses and teachers and students in model
     * @return a String then go to main page for manager
     */
    @GetMapping("/manager-main")
    public String managerMain(Model model) {
        List<Course> courseList = courseController.courses();
        List<StudentDTO> studentList = studentController.students();
        List<TeacherDTO> teacherList = teacherController.teachers();
        model.addAttribute("courses", courseList);
        model.addAttribute("students", studentList);
        model.addAttribute("teachers", teacherList);
        return "Manager";
    }
}
