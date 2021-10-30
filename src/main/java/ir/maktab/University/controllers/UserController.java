package ir.maktab.University.controllers;

import ir.maktab.University.entities.User;
import ir.maktab.University.service.UserService;
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
        if (singedUpUser != null && singedUpUser.getType().equals("Student")) {
            return "redirect:/";
        } else if (singedUpUser != null && singedUpUser.getType().equals("Teacher")) {
            return "redirect:/";
        } else {
            return "redirect:/register";
        }
    }
}
