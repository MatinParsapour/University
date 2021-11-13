package ir.maktab.University.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class TeacherDTO {

    private long id;

    private String firstName;

    private String lastName;

    private Date birthday;

    private long nationalCode;

    private String email;

    private String gender;

    private String status;

    public TeacherDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
