package ir.maktab.University.service;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Quiz;

import java.util.Date;
import java.util.List;

public interface CourseService extends BaseService<Course, Long>{

    /**
     * Get parameters to find course by course code
     * @param courseCode the unique code for each course besides id
     * @return null if there is no match else return the course
     */
    Course getCourseByCourseCode(long courseCode);

    /**
     * @return all the courses that is running
     */
    List<Course> getAllCourses();

    /**
     * Get parameters to delete student from course
     * @param courseId the id of course that manager wants to delete student from
     * @param studentId the id of student that manager wants to delete from course
     * @return a course
     */
    Course removeStudentFromCourse(long courseId, long studentId);

    /**
     * Get the course from controller and set it as active and set manager
     * Pass the course to manager to save for him
     * It will roll back if during creating course disorder occurs
     * @param course the entity that controller pass it to the service to save
     * @return the course that saved in data base
     */
    Course createCourse(Course course);

    /**
     * Set teacher to course
     * It will roll back if during creating course disorder occurs
     * @param teacherId id of the teacher that manager chose
     * @param courseId id of course that manager created the course
     * @return the course that saved in data base
     */
    Course setTeacherToCourse(long teacherId, long courseId);

    /**
     * Set student to course
     * It will roll back if during creating course disorder occurs
     * @param studentId id of the student that manager chose
     * @param courseId id of course that manager chose
     * @return the course that saved
     */
    Course addStudentToCourse(long studentId, long courseId);

    /**
     * Set the status of course in false
     * @param courseId id of course that manager chose to deactivate
     */
    void deActivateCourse(long courseId);

    /**
     * Edit the course information
     * @param courseId the course that manager chose
     * @param newTitle the title that may change
     * @param newCourseCode the course code that may change
     * @param newStartDate the start date that may change
     * @param newFinishDate the finish date that may change
     */
    void editCourseDetails(long courseId, String newTitle, long newCourseCode, Date newStartDate, Date newFinishDate);

    /**
     * Add quiz to the list of course quizzes
     * @param course the course that teacher chose
     * @param quiz the quiz that saved in data base
     */
    void addQuizToCourse(Course course, Quiz quiz);

    /**
     * Search for a course that has the quiz
     * @param quiz search for course by this
     * @return a course that has the quiz
     */
    Course getCourseByQuiz(Quiz quiz);
}
