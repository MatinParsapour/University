package ir.maktab.University.service;

import ir.maktab.University.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User,Long>{

    User getUserByUsernameAndPassword(String username, String password);

    User getUserByUserName(String username);

    List<User> searchUsers(String field,String status, String type, String sex);
}
