package ir.maktab.University.service;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Manager;

public interface ManagerService extends BaseService<Manager,Long> {

    Manager getManagerByUserNameAndPassword(String username, String password);

    void addCourseToManager(Course course);
}
