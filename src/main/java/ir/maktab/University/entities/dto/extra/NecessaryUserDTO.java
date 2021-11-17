package ir.maktab.University.entities.dto.extra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NecessaryUserDTO {

    private String firstName;

    private String lastName;

    private Date birthday;

    private long nationalCode;

    private String email;

    private String type;

    private String status;

    private String gender;
}
