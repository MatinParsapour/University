package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Grade.TABLE_NAME)
public class Grade extends BaseEntity<Long> {
    public static final String TABLE_NAME = "grade_table";
    private static final String QUESTION_POINT = "question_point";

    @Column(name = QUESTION_POINT)
    private Double questionPoint;

    @ManyToMany
    private Set<StudentsGrade> studentsGradeList = new HashSet<>();

}
