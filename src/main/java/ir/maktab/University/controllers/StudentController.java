package ir.maktab.University.controllers;

import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.User;
import ir.maktab.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/get-student")
    public Student getStudent(long id) {
        return studentService.getStudentById(id).get();
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
        return studentService.students();
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
        studentService.addStudent(student);
        return student;
    }

    @PostMapping("/reject-student")
    public void rejectStudent(String userId) {
        Student student = studentService.getStudentById(Long.parseLong(userId)).get();
        student.setStatus("Rejected");
        studentService.addStudent(student);
    }

    @PostMapping("/accept-student")
    public void acceptStudent(String userId) {
        Student student = studentService.getStudentById(Long.parseLong(userId)).get();
        student.setStatus("Accepted");
        studentService.addStudent(student);
    }

    @PostMapping("/delete-student")
    public void deleteStudent(String userId) {
        studentService.deleteStudent(Long.parseLong(userId));
    }

    @PostMapping("/change-to-student")
    public void changeToStudent(User user) {
        Student student = addStudent(user);
        student.setType("Student");
        student.setStatus("Accepted");
        studentService.addStudent(student);
    }
}
