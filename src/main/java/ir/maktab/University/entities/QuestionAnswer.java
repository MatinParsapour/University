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
@Table(name = QuestionAnswer.TABLE_NAME)
public class QuestionAnswer extends BaseEntity<Long> {
    public static final String TABLE_NAME = "question_answer_table";
    private static final String STUDENT_ANSWER = "students_answer";

    @Column(name = STUDENT_ANSWER)
    private String studentAnswer;

    @ManyToOne
    private Student student;
}
