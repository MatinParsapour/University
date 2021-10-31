package ir.maktab.University.service;

import ir.maktab.University.entities.User;

import java.util.Optional;

public interface UserService {

    User getUserByUsernameAndPassword(String username, String password);

    Optional<User> getUserById(long id);

    User getUserByUserName(String username);
}
