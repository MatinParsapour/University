package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
    private static final String IS_ACTIVE = "is_active";


    @Column(name = TITLE)
    private String title;

    @Column(name = COURSE_CODE, unique = true)
    private long courseCode;

    @Column(name = START_DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = FINISH_DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @Column(name = IS_ACTIVE)
    private boolean isActive;

    @ManyToOne
    private Manager manager;

    @OneToMany(mappedBy = "course")
    private Set<Quiz> quizzes;
}
