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
        List<Student> students = studentController.students();
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        model.addAttribute("students",students);
        model.addAttribute("course",course);
        return "ShowCourse";
    }

    @GetMapping("/get-courses")
    public List<Course> courses(){
        return courseService.courses();
    }

    @PostMapping("/remove-student-from-course")
    public String removeStudentFromCourse(String courseId,String studentId){
        Student student = studentController.getStudent(Long.parseLong(studentId));
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        course.getStudents().remove(student);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }

    @PostMapping("/create-course")
    public String createCourse(Course course, String teacherId){
        Course definedCourse = courseService.getCourseByCourseCode(course.getCourseCode());
        if(definedCourse == null){
            Teacher teacher = teacherController.getTeacher(Long.parseLong(teacherId));
            course.setTeacher(teacher);
            courseService.createCourse(course);
            return "redirect:/manager-main";
        }else{
            return "WarningPage";
        }
    }

    @GetMapping("/add-course")
    public String addCourse(Model model){
        List<Teacher> teachers = teacherController.teachers();
        model.addAttribute("teachers",teachers);
        model.addAttribute("course",new Course());
        return "Course";
    }

    @PostMapping("/add-student-to-course")
    public String addStudentToCourse(String studentId, String courseId){
        Student student = studentController.getStudent(Long.parseLong(studentId));
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        course.getStudents().add(student);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }
}
