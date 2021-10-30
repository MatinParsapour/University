package ir.maktab.University.service;

import ir.maktab.University.entities.User;

public interface UserService {

    User getUserByUsernameAndPassword(String username, String password);
}
