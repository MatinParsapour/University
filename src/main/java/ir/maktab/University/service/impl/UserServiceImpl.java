package ir.maktab.University.service.impl;

import ir.maktab.University.entities.User;
import ir.maktab.University.repository.UserRepository;
import ir.maktab.University.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class UserServiceImpl extends BaseServiceImpl<User,Long,UserRepository> implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUserNameAndPassword(username, password);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public List<User> searchUsers(String field) {
        return userRepository.findAllByFirstNameLikeOrLastNameLikeOrStatusLikeOrGenderLikeOrEmailLikeAndIsActiveTrue("%" + field + "%", "%" + field + "%", "%" + field + "%", "%" + field + "%", "%" + field + "%");
    }
}
