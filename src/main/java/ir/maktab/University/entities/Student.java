package ir.maktab.University.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {
    private static final String STATUS = "status";
    private static final String IS_ACTIVE = "is_active";


    @Column(name = STATUS)
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> courseList;

    @Column(name = IS_ACTIVE)
    private boolean isActive;
}
