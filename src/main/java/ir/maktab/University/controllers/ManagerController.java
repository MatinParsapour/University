package ir.maktab.University.controllers;

import ir.maktab.University.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;
}
