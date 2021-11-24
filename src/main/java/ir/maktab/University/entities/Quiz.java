package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = Quiz.TABLE_NAME)
public class Quiz extends BaseEntity<Long> {
    public static final String TABLE_NAME = "quiz_table";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String QUIZ_TIME = "quiz_time";
    private static final String FROM_TIME = "from_time";
    private static final String TO_TIME = "to_time";
    private static final String IS_ACTIVE = "is_active";
    private static final String IN_DATE = "in_date";

    @Column(name = TITLE)
    private String title;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = QUIZ_TIME)
    private Double quizTime;

    @Column(name = FROM_TIME)
    private LocalTime fromTime;

    @Column(name = TO_TIME)
    private LocalTime toTime;

    @Column(name = IN_DATE)
    private LocalDate inDate;

    @Column(name = IS_ACTIVE)
    private Boolean isActive;

    @ManyToOne
    private Course course;

    @OneToMany
    private Set<Questions> questions;

}
