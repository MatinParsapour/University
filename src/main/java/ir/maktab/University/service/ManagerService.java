package ir.maktab.University.service;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Manager;

public interface ManagerService extends BaseService<Manager,Long> {

    /**
     * Add the course to manager
     * @param course the course that has been created
     */
    void addCourseToManager(Course course);
}
