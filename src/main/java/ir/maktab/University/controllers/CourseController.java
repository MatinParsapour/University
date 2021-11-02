package ir.maktab.University.controllers;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.Teacher;
import ir.maktab.University.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherController teacherController;

    @Autowired
    private StudentController studentController;

    @GetMapping("/display-course")
    public String displayCourse(Model model,String courseId){
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        model.addAttribute("course",course);
        return "ShowCourse";
    }

    public void addCourse(Course course){
        courseService.createCourse(course);
    }

    @GetMapping("/get-all-courses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping("/remove-student-from-course")
    public String removeStudentFromCourse(String courseId,String studentId){
        Student student = studentController.getStudent(Long.parseLong(studentId));
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        course.getStudents().remove(student);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }
}
