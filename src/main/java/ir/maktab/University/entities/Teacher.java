package ir.maktab.University.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User {
    private static final String STATUS = "status";

    @Column(name = STATUS)
    private String status;

    @OneToMany(mappedBy = "teacher")
    private List<Course> course;
}
