package ir.maktab.University.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Course> course = new HashSet<>();

    @Column(name = IS_ACTIVE)
    private boolean isActive;

    @OneToOne
    private QuestionsBank questionsBank;
}
