package ir.maktab.University.controllers;

import ir.maktab.University.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
