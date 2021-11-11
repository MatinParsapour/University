package ir.maktab.University.controllers;

import ir.maktab.University.entities.User;
import ir.maktab.University.service.ManagerService;
import ir.maktab.University.service.UserService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    private final UserService userService;


    private final StudentController studentController;


    private final TeacherController teacherController;

    private final ManagerService managerService;

    @Autowired
    public UserController(UserService userService, StudentController studentController, TeacherController teacherController, ManagerService managerService) {
        this.userService = userService;
        this.studentController = studentController;
        this.teacherController = teacherController;
        this.managerService = managerService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("isUserAllow",Security.getIsUserAllow());
        model.addAttribute("user", new User());
        return "Register";
    }

    @PostMapping("/add-user")
    public String addUser(User user) {
        if(userService.getUserByUserName(user.getUserName()) != null){
            return "DoplicateUserName";
        }
        if (user.getType().equals("Student")) {
            studentController.addStudent(user);
            return "SignUpSuccessful";
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
    public String validateUser(User user, Model model) {
        Security.setIsUserAllow("Yes");
        if (studentController.getStudentByUserNameAndPassword(user.getUserName(), user.getPassword()) != null && studentController.getStudentByUserNameAndPassword(user.getUserName(), user.getPassword()).getStatus().equals("Accepted") && studentController.getStudentByUserNameAndPassword(user.getUserName(), user.getPassword()).isActive()) {
            Security.setStudent(studentController.getStudentByUserNameAndPassword(user.getUserName(), user.getPassword()));
            return "redirect:/";
        } else if (teacherController.getTeacherByUserNameAndPassword(user.getUserName(),user.getPassword()) != null && teacherController.getTeacherByUserNameAndPassword(user.getUserName(),user.getPassword()).getStatus().equals("Accepted") && teacherController.getTeacherByUserNameAndPassword(user.getUserName(),user.getPassword()).isActive()) {
            Security.setTeacher(teacherController.getTeacherByUserNameAndPassword(user.getUserName(),user.getPassword()));
            return "redirect:/teacher/main";
        } else if (managerService.getManagerByUserNameAndPassword(user.getUserName(),user.getPassword()) != null && managerService.getManagerByUserNameAndPassword(user.getUserName(),user.getPassword()).isActive()) {
            Security.setManager(managerService.getManagerByUserNameAndPassword(user.getUserName(),user.getPassword()));
            return "redirect:/manager/manager-main";
        } else {
            Security.setIsUserAllow("No");
            return "redirect:/user/register";
        }
    }

    @GetMapping("/log-out")
    public String logOut() {
        Security.setUser(null);
        Security.setStudent(null);
        Security.setManager(null);
        Security.setTeacher(null);
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
        String username = user.getUserName();
        studentController.inActiveStudent(userId);
        teacherController.changeToTeacher(user,username);
        return "redirect:/manager/manager-main";
    }

    @PostMapping("/teacher-to-student")
    public String teacherToStudent(String userId){
        User user = userService.findById(Long.parseLong(userId)).get();
        String username = user.getUserName();
        teacherController.inActiveTeacher(userId);
        studentController.changeToStudent(user,username);
        return "redirect:/manager/manager-main";
    }

    @GetMapping("/search-users")
    public String searchUser(String field, String status, String type, String sex, Model model) {
        List<User> users = userService.searchUsers(field,status,type,sex);
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

    @GetMapping("/sign-up-user")
    public String signUpUser(Model model){
        model.addAttribute("user",new User());
        return "SignUp";
    }
}
