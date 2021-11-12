package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

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
    private static final String IS_ACTIVE = "is_active";

    @Column(name = TITLE)
    private String title;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = QUIZ_TIME)
    private Double quizTime;

    @Column(name = IS_ACTIVE)
    private Boolean isActive;

    @ManyToOne
    private Course course;

    @OneToMany
    private List<Questions> questions;

}
