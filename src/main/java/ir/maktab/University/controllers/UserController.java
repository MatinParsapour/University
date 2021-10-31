package ir.maktab.University.controllers;

import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.User;
import ir.maktab.University.service.UserService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TeacherController teacherController;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "Register";
    }

    @PostMapping("/add-user")
    public String addUser(User user) {
        if (user.getType().equals("Student")) {
            studentController.addStudent(user);
        } else {
            teacherController.addTeacher(user);
        }
        return "redirect:/";
    }

    @PostMapping("/get-right-user")
    public User getRightUser(String username, String password){
        return userService.getUserByUsernameAndPassword(username,password);
    }

    @PostMapping("/validate-user")
    public String validateUser(User user) {
        User singedUpUser = getRightUser(user.getUserName(),user.getPassword());
        if (singedUpUser != null && singedUpUser.getType().equals("Student") && studentController.getStudent(singedUpUser.getId()).getStatus().equals("Accepted")) {
            Security.setUser(singedUpUser);
            return "redirect:/";
        } else if (singedUpUser != null && singedUpUser.getType().equals("Teacher") && teacherController.getTeacher(singedUpUser.getId()).getStatus().equals("Accepted")) {
            Security.setUser(singedUpUser);
            return "redirect:/";
        } else if (singedUpUser != null && singedUpUser.getType().equals("Manager")) {
            Security.setUser(singedUpUser);
            return "redirect:/manager-main";
        } else {
            return "redirect:/register";
        }
    }

    @GetMapping("/log-out")
    public String logOut(){
        Security.setUser(null);
        return "redirect:/";
    }

    @PostMapping("/reject-user")
    public String rejectUser(String userId){
        User user = userService.getUserById(Long.parseLong(userId)).get();
        if(user.getType().equals("Student")){
            studentController.rejectStudent(userId);
            return "redirect:/manager-main";
        }else{
            teacherController.rejectTeacher(userId);
            return "redirect:/manager-main";
        }
    }

    @PostMapping("/accept-user")
    public String acceptUser(String userId){
        User user = userService.getUserById(Long.parseLong(userId)).get();
        if(user.getType().equals("Student")){
            studentController.acceptStudent(userId);
            return "redirect:/manager-main";
        }else{
            teacherController.acceptTeacher(userId);
            return "redirect:/manager-main";
        }
    }

    @PostMapping("/student-to-teacher")
    public String studentToTeacher(String userId){
        User user = userService.getUserById(Long.parseLong(userId)).get();
        studentController.deleteStudent(userId);
        teacherController.changeToTeacher(user);
        return "redirect:/manager-main";
    }
    @PostMapping("/teacher-to-student")
    public String teacherToStudent(String userId){
        User user = userService.getUserById(Long.parseLong(userId)).get();
        teacherController.deleteTeacher(userId);
        studentController.changeToStudent(user);
        return "redirect:/manager-main";
    }
}
