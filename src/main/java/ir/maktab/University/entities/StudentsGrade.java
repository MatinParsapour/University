package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = StudentsGrade.TABLE_NAME)
public class StudentsGrade extends BaseEntity<Long> {
    public static final String TABLE_NAME = "students_grade_table";
    private static final String STUDENT_POINT = "student_point";

    @Column(name = STUDENT_POINT)
    private Double studentPoint;

    @ManyToMany
    private Set<Student> studentList = new HashSet<>();

    @OneToOne(mappedBy = "studentsGrade")
    private Grade grade;
}
