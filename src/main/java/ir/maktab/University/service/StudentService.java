package ir.maktab.University.service;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.User;
import ir.maktab.University.entities.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService extends BaseService<Student,Long>{

    /**
     * Find all students based on dto
     * @return a list of dto
     */
    List<StudentDTO> getAllStudentDTOs();

    /**
     * Search for student by username
     * @param username the username that searched for
     * @return null if no matches found
     */
    Student getStudentByUsername(String username);

    /**
     * @return all the students that accepted by manager
     */
    List<Student> getAllStudents();

    /**
     * Set course for student
     * @param course the course that manager chose
     * @param student the student that manager chose
     */
    void addCourseToStudent(Course course, Student student);

    /**
     * Find student by username and check if the student is accepted or not
     * @param username the username that searched for
     * @return True if student is accepted
     */
    Boolean isStudentAllow(String username);

    /**
     * Create a student based on the form that user filled
     * @param user information about the person that signed up
     * @return a student that saved in data base
     */
    Student createStudent(User user);

    /**
     * Reject the student manager choose when student signed up
     * @param userId id of the user
     */
    void rejectStudent(long userId);

    /**
     * Accept the student manager chose when student signed up
     * @param userId id of the user
     */
    void acceptStudent(long userId);

    /**
     * In active student then don't show the information about the student to manager
     * @param studentId id of student
     */
    void inActiveStudent(long studentId);

    /**
     * Add the information of user to a student and create it
     * @param user the information of user
     * @param username the username of user
     */
    void changeRoleToStudent(User user, String username);
}
