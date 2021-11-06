package ir.maktab.University.controllers;

import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.User;
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

    @PostMapping("/get-student")
    public Student getStudent(long id) {
        return studentService.findById(id).get();
    }


    @PostMapping("/student-allow")
    public Boolean isAllow(String username) {
        Student student = studentService.getStudentByUsername(username);
        if (student.getStatus().equals("Accepted")) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/get-students")
    public List<Student> students() {
        return studentService.getAllStudents();
    }


    public Student addStudent(User user) {
        Student student = new Student();
        student.setStatus("In progerss");
        student.setFirstName(user.getFirstName());
        student.setLastName(user.getLastName());
        student.setBirthday(user.getBirthday());
        student.setUserName(user.getUserName());
        student.setPassword(user.getPassword());
        student.setNationalCode(user.getNationalCode());
        student.setGender(user.getGender());
        student.setEmail(user.getEmail());
        student.setType(user.getType());
        student.setActive(true);
        studentService.save(student);
        return student;
    }

    @PostMapping("/reject-student")
    public void rejectStudent(String userId) {
        Student student = studentService.findById(Long.parseLong(userId)).get();
        student.setStatus("Rejected");
        studentService.save(student);
    }

    @PostMapping("/accept-student")
    public void acceptStudent(String userId) {
        Student student = studentService.findById(Long.parseLong(userId)).get();
        student.setStatus("Accepted");
        studentService.save(student);
    }

    @PostMapping("/delete-student")
    public void inActiveStudent(String userId) {
        Student student = studentService.findById(Long.parseLong(userId)).get();
        student.setActive(false);
        student.setUserName(null);
        studentService.save(student);
    }

    @PostMapping("/change-to-student")
    public void changeToStudent(User user, String username) {
        Student student = addStudent(user);
        student.setType("Student");
        student.setStatus("Accepted");
        student.setUserName(username);
        student.setActive(true);
        studentService.save(student);
    }
}
