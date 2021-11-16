package ir.maktab.University.service.impl;

import ir.maktab.University.entities.User;
import ir.maktab.University.repository.UserRepository;
import ir.maktab.University.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
    public List<User> searchUsers(String field, String status, String type, String sex) {
        return userRepository.findAllByFirstNameLikeOrLastNameLikeAndStatusAndTypeAndGender("%" + field + "%", "%" + field + "%", status,type,sex);
    }

    @Override
    public void editUserInformation(long userId, String firstName, String lastName, Date birthday, String email, long nationalCode) {
        User user = findById(userId).get();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setNationalCode(nationalCode);
        save(user);
    }
}
