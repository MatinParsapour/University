package ir.maktab.University.controllers;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Manager;
import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.Teacher;
import ir.maktab.University.service.CourseService;
import ir.maktab.University.service.ManagerService;
import ir.maktab.University.service.StudentService;
import ir.maktab.University.service.TeacherService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {


    private final CourseService courseService;

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final ManagerService managerService;

    @Autowired
    public CourseController(CourseService courseService, StudentService studentService, TeacherService teacherService, ManagerService managerService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.managerService = managerService;
    }

    @PostMapping("/display-course")
    public String displayCourse(Model model, String courseId) {
        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();
        Course course = courseService.findById(Long.parseLong(courseId)).get();
        model.addAttribute("students", students);
        model.addAttribute("course", course);
        model.addAttribute("teachers", teachers);
        return "ShowCourse";
    }

    @GetMapping("/get-courses")
    public List<Course> courses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/remove-student-from-course")
    public String removeStudentFromCourse(String courseId, String studentId) {
        Student student = studentService.findById(Long.parseLong(studentId)).get();
        Course course = courseService.findById(Long.parseLong(courseId)).get();
        course.getStudents().remove(student);
        courseService.save(course);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/create-course")
    public String createCourse(Course course, String teacherId) {
        Course definedCourse = courseService.getCourseByCourseCode(course.getCourseCode());
        if (definedCourse == null) {
            Teacher teacher = teacherService.findById(Long.parseLong(teacherId)).get();
            course.setTeacher(teacher);
            course.setActive(true);
            Course setCourse = courseService.save(course);
            teacher.getCourse().add(setCourse);
            Manager manager = Security.getManager();
            teacherService.save(teacher);
            manager.getCourses().add(setCourse);
            managerService.save(manager);
            return "redirect:/manager/manager-main";
        } else {
            return "WarningPage";
        }
    }

    @GetMapping("/add-course")
    public String addCourse(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        model.addAttribute("course", new Course());
        return "Course";
    }

    @PostMapping("/add-student-to-course")
    public String addStudentToCourse(String studentId, String courseId) {
        Student student = studentService.findById(Long.parseLong(studentId)).get();
        Course course = courseService.findById(Long.parseLong(courseId)).get();
        course.getStudents().add(student);
        courseService.save(course);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/delete-course")
    public String deleteCourse(String courseId) {
        Course course = courseService.findById(Long.parseLong(courseId)).get();
        course.setActive(false);
        courseService.save(course);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/change-course-name")
    public String changeCourseName(String courseId, String courseName) {
        Course course = courseService.findById(Long.parseLong(courseId)).get();
        course.setTitle(courseName);
        courseService.save(course);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/change-course-code")
    public String changeCourseCode(String courseId, long courseCode) {
        Course course = courseService.findById(Long.parseLong(courseId)).get();
        course.setCourseCode(courseCode);
        courseService.save(course);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/change-course-start-date")
    public String changeCourseStartDate(String courseId, String courseStartDate) throws ParseException {
        Course course = courseService.findById(Long.parseLong(courseId)).get();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(courseStartDate);
        course.setStartDate(startDate);
        courseService.save(course);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/change-course-finish-date")
    public String changeCourseFinishDate(String courseId, String courseFinishDate) throws ParseException {
        Course course = courseService.findById(Long.parseLong(courseId)).get();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date finishDate = format.parse(courseFinishDate);
        course.setFinishDate(finishDate);
        courseService.save(course);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/change-course-teacher")
    public String changeCourseTeacher(long courseId, long teacherId) {
        Course course = courseService.findById(courseId).get();
        Teacher teacher = teacherService.findById(teacherId).get();
        course.setTeacher(teacher);
        courseService.save(course);
        teacher.getCourse().add(course);
        teacherService.save(teacher);
        return "redirect:/manager/manager-main";
    }
}
