package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Manager;
import ir.maktab.University.repository.ManagerRepository;
import ir.maktab.University.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager,Long,ManagerRepository> implements ManagerService {


    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository manegerRepository) {
        super(manegerRepository);
        this.managerRepository = manegerRepository;
    }

    @Override
    public Manager getManagerByUserNameAndPassword(String username, String password) {
        return managerRepository.findByUserNameAndPassword(username, password);
    }
}
