package ir.maktab.University.controllers;

import ir.maktab.University.entities.User;
import ir.maktab.University.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyUserController {


    private final UserService userService;


    private final StudentController studentController;


    private final TeacherController teacherController;

    @Autowired
    public VerifyUserController(UserService userService, StudentController studentController, TeacherController teacherController) {
        this.userService = userService;
        this.studentController = studentController;
        this.teacherController = teacherController;
    }

    @PostMapping("/get-right-user")
    public User getRightUser(String username, String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }

    @PostMapping("/is-user-allowed")
    public String isUserAllow(String username) {
        User user = userService.getUserByUserName(username);
        String result = "";
        if (user.getType().equals("Student")) {
            if (studentController.isAllow(username)) {
                result = "Ok";
            }
        } else if (user.getType().equals("Teacher")) {
            if (teacherController.teacherAllow(username)) {
                result = "Ok";
            }
        } else {
            result = "Ok";
        }
        return result;
    }

    @PostMapping("/is-user-exists")
    public User isUserExists(String username) {
        return userService.getUserByUserName(username);
    }
}
