package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Course;
import ir.maktab.University.entities.Manager;
import ir.maktab.University.repository.ManagerRepository;
import ir.maktab.University.service.ManagerService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager,Long,ManagerRepository> implements ManagerService {


    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        super(managerRepository);
        this.managerRepository = managerRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCourseToManager(Course course) {
        Manager manager = Security.getManager();
        manager.getCourses().add(course);
        save(manager);
    }
}
