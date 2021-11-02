package ir.maktab.University.service.impl;

import ir.maktab.University.entities.User;
import ir.maktab.University.repository.UserRepository;
import ir.maktab.University.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUserNameAndPassword(username,password);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public List<User> searchUsers(String field) {
        return userRepository.findAllByFirstNameLikeOrLastNameLikeOrStatusLikeOrGenderLikeOrEmailLike("%" + field + "%","%" + field + "%","%" + field + "%","%" + field + "%","%" + field + "%");
    }


}
