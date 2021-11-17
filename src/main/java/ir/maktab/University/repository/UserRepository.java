package ir.maktab.University.repository;

import ir.maktab.University.entities.User;
import ir.maktab.University.entities.dto.extra.NecessaryUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameAndPassword(String username, String password);

    User findByUserNameAndIsActiveTrue(String username);

    @Query(value = "SELECT new ir.maktab.University.entities.dto.extra.NecessaryUserDTO(" +
            "u.firstName, u.lastName, u.birthday, u.nationalCode, u.email, " +
            "u.type, u.gender, u.status) FROM User u WHERE u.isActive = true AND u.gender = :gender AND u.status = :status " +
            "AND u.type = :type AND u.firstName LIKE %:name% OR u.lastName LIKE %:name%")
    List<NecessaryUserDTO> findAllUsersByCondition(@Param("name") String name, @Param("status") String status, @Param("type") String type, @Param("gender") String gender);
}
