package ir.maktab.University.service.impl;

import ir.maktab.University.entities.*;
import ir.maktab.University.repository.CourseRepository;
import ir.maktab.University.service.*;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course,Long,CourseRepository> implements CourseService {

    private final CourseRepository courseRepository;

    private final StudentService studentService;

    private final ManagerService managerService;

    private final TeacherService teacherService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentService studentService,
                             ManagerService managerService,
                             TeacherService teacherService) {
        super(courseRepository);
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.managerService = managerService;
        this.teacherService = teacherService;
    }

    @Override
    public Course getCourseByCourseCode(long courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAllByIsActiveTrue();
    }

    @Override
    public Course removeStudentFromCourse(long courseId, long studentId) {
        Student student = studentService.findById(studentId).get();
        Course course = findById(courseId).get();
        course.getStudents().remove(student);
        return save(course);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Course createCourse(Course course) {
        course.setActive(true);
        course.setManager(Security.getManager());
        Course setCourse = save(course);
        managerService.addCourseToManager(setCourse);
        return setCourse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Course setTeacherToCourse(long teacherId, long courseId) {
        Teacher teacher = teacherService.findById(teacherId).get();
        Course course = findById(courseId).get();
        course.setTeacher(teacher);
        Course setCourse = save(course);
        teacherService.addCourseToTeacher(course,teacher);
        return setCourse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Course addStudentToCourse(long studentId, long courseId) {
        Student student = studentService.findById(studentId).get();
        Course course = findById(courseId).get();
        course.getStudents().add(student);
        Course setCourse = save(course);
        studentService.addCourseToStudent(course,student);
        return setCourse;
    }

    @Override
    public void deActivateCourse(long courseId) {
        Course course = findById(courseId).get();
        course.setActive(false);
        save(course);
    }

    @Override
    public void editCourseDetails(long courseId, String newTitle, long newCourseCode, Date newStartDate, Date newFinishDate){
        Course course = findById(courseId).get();
        course.setTitle(newTitle);
        course.setCourseCode(newCourseCode);
        course.setStartDate(newStartDate);
        course.setFinishDate(newFinishDate);
        save(course);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addQuizToCourse(Course course, Quiz quiz) {
        course.getQuizzes().add(quiz);
        save(course);
    }

    @Override
    public Course getCourseByQuiz(Quiz quiz) {
        return courseRepository.findByQuizzes(quiz);
    }

}
