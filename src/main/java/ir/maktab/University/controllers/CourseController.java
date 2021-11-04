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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherController teacherController;

    @Autowired
    private StudentController studentController;

    @PostMapping("/display-course")
    public String displayCourse(Model model, String courseId) {
        List<Student> students = studentController.students();
        List<Teacher> teachers = teacherController.teachers();
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        model.addAttribute("students", students);
        model.addAttribute("course", course);
        model.addAttribute("teachers", teachers);
        return "ShowCourse";
    }

    @GetMapping("/get-courses")
    public List<Course> courses() {
        return courseService.courses();
    }

    @PostMapping("/remove-student-from-course")
    public String removeStudentFromCourse(String courseId, String studentId) {
        Student student = studentController.getStudent(Long.parseLong(studentId));
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        course.getStudents().remove(student);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }

    @PostMapping("/create-course")
    public String createCourse(Course course, String teacherId) {
        Course definedCourse = courseService.getCourseByCourseCode(course.getCourseCode());
        if (definedCourse == null) {
            Teacher teacher = teacherController.getTeacher(Long.parseLong(teacherId));
            course.setTeacher(teacher);
            courseService.createCourse(course);
            return "redirect:/manager-main";
        } else {
            return "WarningPage";
        }
    }

    @GetMapping("/add-course")
    public String addCourse(Model model) {
        List<Teacher> teachers = teacherController.teachers();
        model.addAttribute("teachers", teachers);
        model.addAttribute("course", new Course());
        return "Course";
    }

    @PostMapping("/add-student-to-course")
    public String addStudentToCourse(String studentId, String courseId) {
        Student student = studentController.getStudent(Long.parseLong(studentId));
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        course.getStudents().add(student);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }

    @PostMapping("/delete-course")
    public String deleteCourse(String courseId) {
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        courseService.deleteCourse(course);
        return "redirect:/manager-main";
    }

    @PostMapping("/change-course-name")
    public String changeCourseName(String courseId, String courseName) {
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        course.setTitle(courseName);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }

    @PostMapping("/change-course-code")
    public String changeCourseCode(String courseId, long courseCode) {
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        course.setCourseCode(courseCode);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }

    @PostMapping("/change-course-start-date")
    public String changeCourseStartDate(String courseId, String courseStartDate) throws ParseException {
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(courseStartDate);
        course.setStartDate(startDate);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }

    @PostMapping("/change-course-finish-date")
    public String changeCourseFinishDate(String courseId, String courseFinishDate) throws ParseException {
        Course course = courseService.getCourse(Long.parseLong(courseId)).get();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date finishDate = format.parse(courseFinishDate);
        course.setFinishDate(finishDate);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }

    @PostMapping("/change-course-teacher")
    public String changeCourseTeacher(long courseId, long teacherId) {
        Course course = courseService.getCourse(courseId).get();
        Teacher teacher = teacherController.getTeacher(teacherId);
        course.setTeacher(teacher);
        courseService.createCourse(course);
        return "redirect:/manager-main";
    }
}
