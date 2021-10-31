package ir.maktab.University.controllers;

import ir.maktab.University.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("user", Security.getUser());
        return "Home";
    }
}
