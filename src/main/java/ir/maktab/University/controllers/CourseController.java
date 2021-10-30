package ir.maktab.University.controllers;

import ir.maktab.University.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
}
