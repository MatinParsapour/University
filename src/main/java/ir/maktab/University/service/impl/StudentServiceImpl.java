package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Role;
import ir.maktab.University.entities.Student;
import ir.maktab.University.entities.User;
import ir.maktab.University.entities.dto.StudentDTO;
import ir.maktab.University.entities.dto.UserDTO;
import ir.maktab.University.repository.StudentRepository;
import ir.maktab.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,Long,StudentRepository> implements StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> getAllStudentDTOs() {
        return studentRepository.findStudentDTOs();
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUserName(username);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAllByIsActiveTrue();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCourseToStudent(Course course, Student student) {
        student.getCourseList().add(course);
        save(student);
    }

    @Override
    public Boolean isStudentAllow(String username) {
        Student student = getStudentByUsername(username);
        return student.getStatus().equals("Accepted");
    }

    @Override
    public Student createStudent(UserDTO user) {
        Student student = new Student();
        Role role = new Role();
        role.setRoleName("STUDENT");
        student.setStatus("In progerss");
        student.setFirstName(user.getFirstName());
        student.setLastName(user.getLastName());
        student.setBirthday(user.getBirthday());
        student.setUserName(user.getUserName());
        student.setPassword(user.getPassword());
        student.setNationalCode(user.getNationalCode());
        student.setGender(user.getGender());
        student.setEmail(user.getEmail());
        student.setType(user.getType());
        student.setActive(true);
        student.getRoles().add(role);
        return save(student);
    }

    @Override
    public void rejectStudent(long userId) {
        Student student = findById(userId).get();
        student.setStatus("Rejected");
        save(student);
    }

    @Override
    public void acceptStudent(long userId) {
        Student student = findById(userId).get();
        student.setStatus("Accepted");
        save(student);
    }

    @Override
    public void inActiveStudent(long studentId) {
        Student student = findById(studentId).get();
        student.setActive(false);
        save(student);
    }

    @Override
    public void changeRoleToStudent(User user, String username) {
        Student definedStudent = studentRepository.findByUserName(username);
        if(definedStudent == null){
            Student student = (Student) user;
            student.setType("Student");
            student.setStatus("Accepted");
            student.setUserName(username);
            student.setActive(true);
            save(student);
        }else{
            definedStudent.setActive(true);
            save(definedStudent);
        }
    }
}
