package ir.maktab.University.restcontrollers;

import ir.maktab.University.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/course-rest")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Edit course data and save it to data base
     * @param courseId the id of course that manager made changes
     * @param title the title that may have changed
     * @param courseCode the course code that may have changed
     * @param startDate the start date that may have changed
     * @param finishDate the finish date that may have changed
     */
    @PostMapping("/update-course-data")
    public void updateCourseData(long courseId, String title, long courseCode, Date startDate, Date finishDate){
        courseService.editCourseDetails(courseId,title,courseCode,startDate,finishDate);
    }

    @PostMapping("/add-student-to-course")
    public void addStudentToCourse(long studentId, long courseId){
        courseService.addStudentToCourse(studentId,courseId);
    }

    @PostMapping("/remove-student-from-course")
    public void removeStudentFromCourse(long studentId, long courseId){
        courseService.removeStudentFromCourse(courseId,studentId);
    }

    @PostMapping("/set-teacher-to-course")
    public void setTeacherToCourse(long teacherId, long courseId){
        courseService.setTeacherToCourse(teacherId,courseId);
    }
}
