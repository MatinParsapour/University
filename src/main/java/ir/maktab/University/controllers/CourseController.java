package ir.maktab.University.controllers;

import ir.maktab.University.entities.*;
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

    /**
     * Display the information about the course that manager chose
     * Manager can change teacher of course and can add or delete
     * Student from course
     * @param model set course, teachers and students to display for manager
     * @param courseId the course that manager chose to display information about it
     * @return a String to redirect to the pager
     */
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

    /**
     * Connect to service and remove student from course
     * @param courseId the id of course that manager wants to delete student from and gets from site
     * @param studentId the id of student that will delete form course and gets from site
     * @return a String to go to the main page for manager
     */
    @PostMapping("/remove-student-from-course")
    public String removeStudentFromCourse(long courseId, long studentId) {
        courseService.removeStudentFromCourse(courseId,studentId);
        return "redirect:/manager/manager-main";
    }

    /**
     * The course that manager set information about it come to this method
     * Check if the course code is already defined or not
     * If already defined redirect to another page else
     * The course will create
     * @param course an entity that filled with title and code and start date and finish date
     * @param model that sets a list of teachers and the course that created for manager to set teacher
     * @return a String to go to the page
     */
    @PostMapping("/create-course")
    public String createCourse(Course course, Model model) {
        Course definedCourse = courseService.getCourseByCourseCode(course.getCourseCode());
        if (definedCourse == null) {
            Course setCourse = courseService.createCourse(course);
            List<Teacher> teachers = teacherService.getAllTeachers();
            model.addAttribute("teachers", teachers);
            model.addAttribute("course",setCourse);
            return "SelectTeacher";
        } else {
            return "WarningPage";
        }
    }

    /**
     * Set the two way relationship between course and teacher
     * It will rol back if during setting disorder occurs
     * @param teacherId the id of teacher manager wants to add to student
     * @param courseId the course that manager created before
     * @return a String to redirect to main page for manager
     */
    @PostMapping("/set-teacher-to-course")
    public String setTeacherToCourse(long teacherId,long courseId){
        courseService.setTeacherToCourse(teacherId,courseId);
        return "redirect:/manager/manager-main";
    }

    /**
     * Display the page for manager to create new course
     * @param model create new course
     * @return a String to redirect to the page for manager to create new course
     */
    @GetMapping("/add-course")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "Course";
    }

    /**
     * Set the two way relationship between course and student
     * @param studentId id of student manager chose to add to course
     * @param courseId id of course that manager chose to add student to
     * @return a String to redirect to main page for manager
     */
    @PostMapping("/add-student-to-course")
    public String addStudentToCourse(long studentId, long courseId) {
        courseService.addStudentToCourse(studentId,courseId);
        return "redirect:/manager/manager-main";
    }

    /**
     * Deactivate course that manager chose to delete
     * @param courseId id of course
     * @return a String to redirect to main page for manager
     */
    @PostMapping("/delete-course")
    public String deleteCourse(long courseId) {
        courseService.deActivateCourse(courseId);
        return "redirect:/manager/manager-main";
    }

    /**
     * Edit the course information about course
     * @param courseId id of course that manager wants to edit
     * @param newTitle the title that may change
     * @param newCourseCode the course code that may change
     * @param newStartDate the start date that may change
     * @param newFinishDate the finish date that may change
     * @return a String to redirect to main page for manager
     * @throws ParseException
     */
    @PostMapping("/change-course-details")
    public String changeCourseDetails(long courseId, String newTitle, long newCourseCode, String newStartDate, String newFinishDate) throws ParseException {
        courseService.editCourseDetails(courseId,newTitle,newCourseCode,newStartDate,newFinishDate);
        return "redirect:/manager/manager-main";
    }

    /**
     * Display the information about course for teacher
     * @param model set the course and a new quiz in model to create a new quiz
     * @param courseId id of course that teacher wants to see details
     * @return a String then go to the page to show course details
     */
    @PostMapping("/course-details")
    public String courseDetails(Model model, long courseId){
        Course course = courseService.findById(courseId).get();
        model.addAttribute("course",course);
        model.addAttribute("quiz",new Quiz());
        return "CourseDetails";
    }
}
