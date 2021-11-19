package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = BaseQuestion.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseQuestion extends BaseEntity<Long> {
    public static final String TABLE_NAME = "question_table";
    private static final String TITLE = "title";
    private static final String HEADER = "header";
    private static final String CORRECT_ANSWER = "correct_answer";

    @Column(name = TITLE)
    private String title;

    @Column(name = HEADER)
    private String header;

    @Column(name = CORRECT_ANSWER)
    private String CorrectAnswer;
}
