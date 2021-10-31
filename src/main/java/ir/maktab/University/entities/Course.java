package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = Course.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity<Long> {
    public static final String TABLE_NAME = "course_table";
    private static final String TITLE = "title";
    private static final String COURSE_CODE = "course_code";
    private static final String START_DATE = "start_date";
    private static final String FINISH_DATE = "finish_date";

    @Column(name = TITLE)
    private String title;

    @Column(name = COURSE_CODE, unique = true)
    private long courseCode;

    @Column(name = START_DATE)
    private LocalDate startDate;

    @Column(name = FINISH_DATE)
    private LocalDate finishDate;

    @OneToOne
    private Teacher teacher;

    @OneToMany
    private Set<Student> students;
}