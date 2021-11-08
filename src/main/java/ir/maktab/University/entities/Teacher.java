package ir.maktab.University.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User {
    private static final String STATUS = "status";
    private static final String IS_ACTIVE = "is_active";


    @Column(name = STATUS)
    private String status;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.EAGER)
    private List<Course> course;

    @Column(name = IS_ACTIVE)
    private boolean isActive;
}
