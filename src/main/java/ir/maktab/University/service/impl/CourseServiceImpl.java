package ir.maktab.University.service.impl;

import ir.maktab.University.repository.CourseRepository;
import ir.maktab.University.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
}
