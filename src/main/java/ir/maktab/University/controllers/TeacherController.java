package ir.maktab.University.controllers;

import ir.maktab.University.entities.Teacher;
import ir.maktab.University.entities.User;
import ir.maktab.University.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {


    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/teacher-allow")
    public Boolean teacherAllow(String username) {
        Teacher teacher = teacherService.getTeacherByUserName(username);
        if (teacher.getStatus().equals("Accepted")) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/get-teachers")
    public List<Teacher> teachers() {
        return teacherService.getAllTeachers();
    }


    @PostMapping("/get-teacher")
    public Teacher getTeacher(long id) {
        return teacherService.findById(id).get();
    }

    public Teacher addTeacher(User user) {
        Teacher teacher = new Teacher();
        teacher.setStatus("In progress");
        teacher.setFirstName(user.getFirstName());
        teacher.setLastName(user.getLastName());
        teacher.setBirthday(user.getBirthday());
        teacher.setUserName(user.getUserName());
        teacher.setPassword(user.getPassword());
        teacher.setNationalCode(user.getNationalCode());
        teacher.setGender(user.getGender());
        teacher.setEmail(user.getEmail());
        teacher.setType(user.getType());
        teacher.setActive(true);
        teacherService.save(teacher);
        return teacher;
    }

    @PostMapping("/reject-teacher")
    public void rejectTeacher(String userId) {
        Teacher teacher = teacherService.findById(Long.parseLong(userId)).get();
        teacher.setStatus("Rejected");
        teacherService.save(teacher);
    }

    @PostMapping("/accept-teacher")
    public void acceptTeacher(String userId) {
        Teacher teacher = teacherService.findById(Long.parseLong(userId)).get();
        teacher.setStatus("Accepted");
        teacher.setActive(true);
        teacherService.save(teacher);
    }

    @PostMapping("/change-to-teacher")
    public void changeToTeacher(User user,String username) {
        Teacher teacher = addTeacher(user);
        teacher.setType("Teacher");
        teacher.setStatus("Accepted");
        teacher.setUserName(username);
        teacherService.save(teacher);
    }

    @PostMapping("/delete-teacher")
    public void inActiveTeacher(String userId) {
        Teacher teacher = teacherService.findById(Long.parseLong(userId)).get();
        teacher.setActive(false);
        teacher.setUserName(null);
        teacherService.save(teacher);
    }

    @PostMapping("/get-teacher-by-username-and-password")
    public Teacher getTeacherByUserNameAndPassword(String username,String password){
        return teacherService.getTeacherByUserNameAndPassword(username,password);
    }
}
