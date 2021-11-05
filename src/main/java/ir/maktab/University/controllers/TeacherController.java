package ir.maktab.University.controllers;

import ir.maktab.University.entities.Teacher;
import ir.maktab.University.entities.User;
import ir.maktab.University.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @PostMapping("/teacher/teacher-allow")
    public Boolean teacherAllow(String username) {
        Teacher teacher = teacherService.getTeacherByUserName(username);
        if (teacher.getStatus().equals("Accepted")) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/teacher/get-teachers")
    public List<Teacher> teachers() {
        return teacherService.getAllTeachers();
    }


    @PostMapping("/teacher/get-teacher")
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

    @PostMapping("/teacher/reject-teacher")
    public void rejectTeacher(String userId) {
        Teacher teacher = teacherService.findById(Long.parseLong(userId)).get();
        teacher.setStatus("Rejected");
        teacherService.save(teacher);
    }

    @PostMapping("/teacher/accept-teacher")
    public void acceptTeacher(String userId) {
        Teacher teacher = teacherService.findById(Long.parseLong(userId)).get();
        teacher.setStatus("Accepted");
        teacher.setActive(true);
        teacherService.save(teacher);
    }

    @PostMapping("/teacher/change-to-teacher")
    public void changeToTeacher(User user,String username) {
        Teacher teacher = addTeacher(user);
        teacher.setType("Teacher");
        teacher.setStatus("Accepted");
        teacher.setUserName(username);
        teacherService.save(teacher);
    }

    @PostMapping("/teacher/delete-teacher")
    public void inActiveTeacher(String userId) {
        Teacher teacher = teacherService.findById(Long.parseLong(userId)).get();
        teacher.setActive(false);
        teacher.setUserName(null);
        teacherService.save(teacher);
    }
}
