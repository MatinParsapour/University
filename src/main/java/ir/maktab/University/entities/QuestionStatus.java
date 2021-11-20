package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = QuestionStatus.TABLE_NAME)
public class QuestionStatus extends BaseEntity<Long> {
    public static final String TABLE_NAME = "question_status_table";
    private static final String QUESTION_POINT = "question_point";

    @OneToOne
    private QuestionHeader questionHeader;

    @Column(name = QUESTION_POINT)
    private double questionPoint;

    @OneToMany
    private List<StudentResult> studentResultList;

}
