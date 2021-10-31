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


    @PostMapping("/get-teacher")
    public Teacher getTeacher(long id){
        return teacherService.getTeacherById(id).get();
    }

    public Teacher addTeacher(User user){
        Teacher teacher = new Teacher();
        teacher.setStatus("In progress");
        teacher.setFirstName(user.getFirstName());
        teacher.setLastName(user.getLastName());
        teacher.setBirthday(user.getBirthday());
        teacher.setUserName(user.getUserName());
        teacher.setPassword(user.getPassword());
        teacher.setNationalCode(user.getNationalCode());
        teacher.setEmail(user.getEmail());
        teacher.setType(user.getType());
        teacherService.addTeacher(teacher);
        return teacher;
    }

    @GetMapping("/get-teachers")
    public List<Teacher> teachers(){
        return teacherService.getAllTeachers();
    }

    @PostMapping("/reject-teacher")
    public void rejectTeacher(String userId){
        Teacher teacher = teacherService.getTeacherById(Long.parseLong(userId)).get();
        teacher.setStatus("Rejected");
        teacherService.addTeacher(teacher);
    }

    @PostMapping("/accept-teacher")
    public void acceptTeacher(String userId){
        Teacher teacher = teacherService.getTeacherById(Long.parseLong(userId)).get();
        teacher.setStatus("Accepted");
        teacherService.addTeacher(teacher);
    }

    @PostMapping("/change-to-teacher")
    public void changeToTeacher(User user){
        Teacher teacher = addTeacher(user);
        teacher.setStatus("Accepted");
        teacherService.addTeacher(teacher);
    }

    @PostMapping("/delete-teacher")
    public void deleteTeacher(String userId){
        teacherService.deleteTeacher(Long.parseLong(userId));
    }
}
