package ir.maktab.University.repository;

import ir.maktab.University.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameAndPassword(String username, String password);

    User findByUserName(String username);

    List<User> findAllByFirstNameLikeOrLastNameLikeAndStatusAndTypeAndGender(String firstName, String lastName, String status, String type, String gender);
}
