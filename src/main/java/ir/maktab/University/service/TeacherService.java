package ir.maktab.University.service;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.Teacher;
import ir.maktab.University.entities.User;

import java.util.List;
import java.util.Optional;

public interface TeacherService extends BaseService<Teacher,Long> {

    /**
     * search for the teacher by username
     * @param username the username that searched for
     * @return null if the no matches found else return the teacher
     */
    Teacher getTeacherByUserName(String username);

    /**
     * @return All the teachers that accepted by manager
     */
    List<Teacher> getAllTeachers();

    /**
     * Set course for teacher
     * @param course the course that sets for teacher
     * @param teacher the teacher that manager chose for this course
     */
    void addCourseToTeacher(Course course, Teacher teacher);

    /**
     * Find teacher by username and check if the teacher is accepted or not
     * @param username the username that searched for
     * @return True if student is accepted
     */
    Boolean isTeacherAllow(String username);

    /**
     * Create a teacher based on the form that user filled
     * @param user information about the person that signed up
     * @return a student that saved in data base
     */
    Teacher createTeacher(User user);

    /**
     * Reject the teacher manager choose when teacher signed up
     * @param userId id of the user
     */
    void rejectTeacher(long userId);

    /**
     * Accept the teacher manager chose when teacher signed up
     * @param userId id of the user
     */
    void acceptTeacher(long userId);

    /**
     * In active teacher then don't show the information about the teacher to manager
     * @param teacherId id of teacher
     */
    void inActiveTeacher(long teacherId);

    /**
     * Add the information of user to a teacher and create it
     * @param user the information of user
     * @param username the username of user
     */
    void changeRoleToTeacher(User user, String username);
}
