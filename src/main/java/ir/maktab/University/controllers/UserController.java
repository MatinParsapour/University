package ir.maktab.University.controllers;

import ir.maktab.University.config.AuthenticationProviderSecurity;
import ir.maktab.University.config.UserDetailsSecurity;
import ir.maktab.University.config.UserDetailsServiceSecurity;
import ir.maktab.University.entities.*;
import ir.maktab.University.entities.dto.UserDTO;
import ir.maktab.University.entities.dto.extra.NecessaryUserDTO;
import ir.maktab.University.entities.dto.extra.UserUPDTO;
import ir.maktab.University.restcontrollers.TeacherController;
import ir.maktab.University.service.*;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    private final TeacherService teacherService;

    private final ManagerService managerService;

    private final StudentService studentService;

    private final UserDetailsServiceSecurity userDetailsServiceSecurity;

    private final AuthenticationProviderSecurity authenticationProviderSecurity;

    @Autowired
    public UserController(UserService userService, StudentController studentController,
                          TeacherController teacherController, TeacherService teacherService, ManagerService managerService,
                          StudentService studentService, UserDetailsServiceSecurity userDetailsServiceSecurity,
                          AuthenticationProviderSecurity authenticationProviderSecurity) {
        this.userService = userService;
        this.studentController = studentController;
        this.teacherController = teacherController;
        this.teacherService = teacherService;
        this.managerService = managerService;
        this.studentService = studentService;
        this.userDetailsServiceSecurity = userDetailsServiceSecurity;
        this.authenticationProviderSecurity = authenticationProviderSecurity;
    }

    /**
     * Show a message to user is user doesn't allow to enter
     * Create a new entity to get information of user when log in
     * @param model add the message and entity to model
     * @return a String then redirect to the log in page
     */
    @GetMapping("/login")
    public String register(Model model) {
        model.addAttribute("isUserAllow",Security.getIsUserAllow());
        model.addAttribute("user", new UserUPDTO());
        return "Register";
    }

    /**
     * Check username if is doplicate or not
     * Check type of user when sign up and create a new user base on type
     * @param user the information of user includes : First name, Last name, National code, email, gender,...
     * @return a String to return to proper page
     */
    @PostMapping("/add-user")
    public String addUser(UserDTO user) {
        if(userService.getUserByUserName(user.getUserName()) != null){
            return "DoplicateUserName";
        }
        if (user.getType().equals("Student")) {
            studentController.addStudent(user);
            return "SignUpSuccessful";
        } else {
            teacherController.addTeacher(user);
            return "SignUpSuccessful";
        }
    }

    /**
     * Based on username and password that entered get the user
     * @param username the username that user entered
     * @param password the password that user entered
     * @return a null if no matches found
     */
    @PostMapping("/get-correct-user")
    public User getRightUser(String username, String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }

    /**
     * Validate user if is active and user name and password is correct
     * @param user includes the username and password
     * @return a String to return to relate page to manager teacher or student
     */
    @PostMapping("/validate-user")
    public String validateUser(UserUPDTO user) {
        authenticationProviderSecurity.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(),user.getPassword()
                )
        );
        UserDetailsSecurity userDetailsSecurity = (UserDetailsSecurity) userDetailsServiceSecurity.loadUserByUsername(user.getUserName());
        if(userDetailsSecurity.getUser().getType().equals("Manager")){
            Manager manager = managerService.findById(userDetailsSecurity.getUser().getId()).get();
            Security.setManager(manager);
            return "redirect:/manager/manager-main";
        }else if(userDetailsSecurity.getUser().getType().equals("Student")){
            Student student = studentService.findById(userDetailsSecurity.getUser().getId()).get();
            Security.setStudent(student);
            return "redirect:/student/student-main";
        }else{
            Teacher teacher = teacherController.getTeacher(userDetailsSecurity.getUser().getId());
            Security.setTeacher(teacher);
            return "redirect:/teacher/main";
        }
    }

    /**
     * Set security properties in null to log out user
     * @return a String to redirect to main page
     */
    @GetMapping("/log-out")
    public String logOut() {
        Security.setUser(null);
        Security.setStudent(null);
        Security.setManager(null);
        Security.setTeacher(null);
        return "redirect:/";
    }

    /**
     * Check type of user and send information to the method to reject student or teacher
     * @param userId id of user that manager wants to reject
     * @return a String to redirect to main page for manager
     */
    @PostMapping("/reject-user")
    public String rejectUser(long userId) {
        User user = userService.findById(userId).get();
        if (user.getType().equals("Student")) {
            studentController.rejectStudent(userId);
            return "redirect:/manager/manager-main";
        } else {
            teacherService.rejectTeacher(userId);
            return "redirect:/manager/manager-main";
        }
    }

    /**
     * Check type of user and send information to the method to accept student or teacher
     * @param userId id of user that manager wants to accept
     * @return a String to redirect to main page for manager
     */
    @PostMapping("/accept-user")
    public String acceptUser(long userId) {
        User user = userService.findById(userId).get();
        if (user.getType().equals("Student")) {
            studentController.acceptStudent(userId);
            return "redirect:/manager/manager-main";
        } else {
            teacherService.acceptTeacher(userId);
            return "redirect:/manager/manager-main";
        }
    }

    /**
     * Change role of student to teacher
     * Find user by id
     * save username
     * in active student
     * change to teacher by user information and username
     * @param userId the id of student that manager wants to change to teacher
     * @return a String to redirect to main page of manager
     */
    @PostMapping("/student-to-teacher")
    public String studentToTeacher(long userId) {
        User user = userService.findById(userId).get();
        String username = user.getUserName();
        studentController.inActiveStudent(userId);
        teacherController.changeToTeacher(user,username);
        return "redirect:/manager/manager-main";
    }

    /**
     * Change role of teacher to student
     * Find user by id
     * save username
     * in active teacher
     * change to student by user information and username
     * @param userId the id of student that manager wants to change to student
     * @return a String to redirect to main page of manager
     */
    @PostMapping("/teacher-to-student")
    public String teacherToStudent(long userId){
        User user = userService.findById(userId).get();
        String username = user.getUserName();
        teacherController.inActiveTeacher(userId);
        studentController.changeToStudent(user,username);
        return "redirect:/manager/manager-main";
    }

    /**
     * Get all the filters that applied on the search and sen it to serivce to find the user
     * @param field the first name or last name or email or... that manager searched for
     * @param status in progress, accepted, rejected
     * @param type student, teacher
     * @param sex male, female
     * @param model set all the results in a model and send it to view
     * @return a String to redirect to found users page
     */
    @GetMapping("/search-users")
    public String searchUser(String field, String status, String type, String sex, Model model) {
        List<NecessaryUserDTO> users = userService.searchUsers(field,status,type,sex);
        if (users.size() == 0) {
            model.addAttribute("users", null);
        } else {
            model.addAttribute("users", users);
        }
        return "FoundUsers";
    }

    /**
     * Find the user that manager wants to change information by id
     * @param userId the id of user that manager is looking for
     * @param model set the user in a model and send to view
     * @return a String to redirect to Edit user page
     */
    @PostMapping("/edit-user-information")
    public String editUserInformation(String userId, Model model) {
        User user = userService.findById(Long.parseLong(userId)).get();
        model.addAttribute("user", user);
        return "EditUserInformation";
    }

    /**
     * Create a new model for user to enter all the information about him
     * @param model set new user
     * @return a String to redirect to sign up page
     */
    @GetMapping("/sign-up-user")
    public String signUpUser(Model model){
        model.addAttribute("user",new UserDTO());
        return "SignUp";
    }
}
