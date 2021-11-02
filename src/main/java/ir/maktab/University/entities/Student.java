package ir.maktab.University.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User{
    private static final String STATUS = "status";

    @Column(name = STATUS)
    private String status;

    @ManyToMany(mappedBy = "students")
    private List<Course> courseList;
}
