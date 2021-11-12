package ir.maktab.University.controllers;

import ir.maktab.University.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * Check if a user(Teacher, Student, Manager) is logged in or not
     * @param model set the user in model to show in page
     * @return a String then go to main page
     */
    @GetMapping("/")
    public String home(Model model) {
        if(Security.getManager() != null){
            model.addAttribute("user", Security.getManager());
        }else if(Security.getTeacher() != null){
            model.addAttribute("user",Security.getTeacher());
        }else if(Security.getStudent() != null){
            model.addAttribute("user",Security.getStudent());
        }else{
            model.addAttribute("user",null);
        }
        return "Home";
    }
}
