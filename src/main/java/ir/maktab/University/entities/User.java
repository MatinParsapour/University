package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Inheritance
@Table(name = User.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<Long> {
    public static final String TABLE_NAME = "user_table";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIRTHDAY = "birthday";
    private static final String NATIONAL_CODE = "national_code";
    private static final String EMAIL = "email";
    private static final String TYPE = "type";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = BIRTHDAY)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column(name = NATIONAL_CODE)
    private long nationalCode;

    @Column(name = EMAIL)
    private String email;

    @Column(name = TYPE)
    private String type;

    @Column(name = USER_NAME,unique = true)
    private String userName;

    @Column(name = PASSWORD)
    private String password;
}
