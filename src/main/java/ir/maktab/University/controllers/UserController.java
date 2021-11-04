package ir.maktab.University.controllers;

import ir.maktab.University.entities.User;
import ir.maktab.University.service.UserService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @PostMapping("/get-correct-user")
    public User getRightUser(String username, String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }

    @PostMapping("/validate-user")
    public String validateUser(User user) {
        User singedUpUser = getRightUser(user.getUserName(), user.getPassword());
        if (singedUpUser != null && singedUpUser.getType().equals("Student") && studentController.getStudent(singedUpUser.getId()).getStatus().equals("Accepted")) {
            Security.setUser(singedUpUser);
            return "redirect:/";
        } else if (singedUpUser != null && singedUpUser.getType().equals("Teacher") && teacherController.getTeacher(singedUpUser.getId()).getStatus().equals("Accepted")) {
            Security.setUser(singedUpUser);
            return "redirect:/";
        } else if (singedUpUser != null && singedUpUser.getType().equals("Manager")) {
            Security.setUser(singedUpUser);
            return "redirect:/manager/manager-main";
        } else {
            return "redirect:/register";
        }
    }

    @GetMapping("/log-out")
    public String logOut() {
        Security.setUser(null);
        return "redirect:/";
    }

    @PostMapping("/reject-user")
    public String rejectUser(String userId) {
        User user = userService.findById(Long.parseLong(userId)).get();
        if (user.getType().equals("Student")) {
            studentController.rejectStudent(userId);
            return "redirect:/manager/manager-main";
        } else {
            teacherController.rejectTeacher(userId);
            return "redirect:/manager/manager-main";
        }
    }

    @PostMapping("/accept-user")
    public String acceptUser(String userId) {
        User user = userService.findById(Long.parseLong(userId)).get();
        if (user.getType().equals("Student")) {
            studentController.acceptStudent(userId);
            return "redirect:/manager/manager-main";
        } else {
            teacherController.acceptTeacher(userId);
            return "redirect:/manager/manager-main";
        }
    }

    @PostMapping("/student-to-teacher")
    public String studentToTeacher(String userId) {
        User user = userService.findById(Long.parseLong(userId)).get();
        studentController.deleteStudent(userId);
        teacherController.changeToTeacher(user);
        return "redirect:/manager/manager-main";
    }

    @GetMapping("/search-users")
    public String searchUser(String field, Model model) {
        List<User> users = userService.searchUsers(field);
        if (users.size() == 0) {
            model.addAttribute("users", null);
        } else {
            model.addAttribute("users", users);
        }
        return "FoundUsers";
    }

    @PostMapping("/change-user-first-name")
    public String changeUserFirstName(String userId, String firstName) {
        User user = userService.findById(Long.parseLong(userId)).get();
        user.setFirstName(firstName);
        userService.save(user);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/change-user-last-name")
    public String changeUserLastName(String userId, String lastName) {
        User user = userService.findById(Long.parseLong(userId)).get();
        user.setLastName(lastName);
        userService.save(user);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/change-user-email")
    public String changeUserEmail(String userId, String email) {
        User user = userService.findById(Long.parseLong(userId)).get();
        user.setEmail(email);
        userService.save(user);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/edit-user-information")
    public String editUserInformation(String userId, Model model) {
        User user = userService.findById(Long.parseLong(userId)).get();
        model.addAttribute("user", user);
        return "EditUserInformation";
    }
}
