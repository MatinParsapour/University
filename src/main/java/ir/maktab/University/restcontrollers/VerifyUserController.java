package ir.maktab.University.restcontrollers;

import ir.maktab.University.controllers.StudentController;
import ir.maktab.University.entities.User;
import ir.maktab.University.restcontrollers.TeacherController;
import ir.maktab.University.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/update-user")
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

    /**
     * Get all data from front and set them to data base
     * @param firstName the first name may have changed
     * @param lastName the last name may have changed
     * @param birthday the birthday may have changed
     * @param email the email may have changed
     * @param nationalCode the national code may have changed
     * @param id the id of the user
     */
    @PostMapping("/update-user-information")
    public void updateUserInformation(String firstName, String lastName, Date birthday, String email, long nationalCode, long id){
        userService.editUserInformation(id,firstName,lastName,birthday,email,nationalCode);
    }
}
