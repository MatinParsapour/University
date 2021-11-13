package ir.maktab.University.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class StudentDTO {

    private long id;

    private String firstName;

    private String lastName;

    private Date birthday;

    private long nationalCode;

    private String email;

    private String gender;

    private String status;
}
