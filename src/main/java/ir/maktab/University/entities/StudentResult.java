package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = StudentResult.TABLE_NAME)
public class StudentResult extends BaseEntity<Long> {
    public static final String TABLE_NAME = "student_result_table";
    private static final String STUDENT_ANSWER = "student_answer";
    private static final String STUDENT_POINT = "student_point";

    @Column(name = STUDENT_ANSWER)
    private String studentAnswer;

    @ManyToMany
    private Set<Student> student;

    @Column(name = STUDENT_POINT)
    private double studentPoint;
}
