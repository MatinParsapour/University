package ir.maktab.University.service;

import ir.maktab.University.entities.User;
import ir.maktab.University.entities.dto.extra.NecessaryUserDTO;

import java.util.Date;
import java.util.List;

public interface UserService extends BaseService<User,Long>{

    /**
     * Find user by username and password for log in
     * @param username the username that user entered
     * @param password the password that user entered
     * @return null if no matches found else return the user
     */
    User getUserByUsernameAndPassword(String username, String password);

    /**
     * Find user by username to check if it is doplicate or not
     * @param username the username that user entered
     * @return null if no matches found else return the user
     */
    User getUserByUserName(String username);

    /**
     * Search for the user by filters
     * @param field the field include : First name, Last name, Email ,...
     * @param status In progress, Accepted, Rejected
     * @param type Student, Teacher
     * @param sex male, female
     * @return a list of users found with these filters
     */
    List<NecessaryUserDTO> searchUsers(String field, String status, String type, String sex);

    /**
     * Get the new information and set them in the user and save them
     * @param userId id of user that manager wants to edit information
     * @param firstName the firstname that may changed
     * @param lastName the lastname that may changed
     * @param birthday the birthday that may changed
     * @param email the email that may changed
     * @param nationalCode the national code that may changed
     */
    void editUserInformation(long userId, String firstName, String lastName, Date birthday, String email, long nationalCode);
}
