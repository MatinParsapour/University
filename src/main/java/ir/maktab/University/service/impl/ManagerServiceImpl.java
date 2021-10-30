package ir.maktab.University.service.impl;

import ir.maktab.University.repository.ManagerRepository;
import ir.maktab.University.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
}
