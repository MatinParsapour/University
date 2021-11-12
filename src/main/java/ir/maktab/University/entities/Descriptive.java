package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Descriptive.TABLE_NAME)
public class Descriptive extends BaseEntity<Long> {
    public static final String TABLE_NAME = "descriptive_table";
    private static final String TITLE = "title";
    private static final String HEADER = "header";
    private static final String CORRECT_ANSWER = "correct_answer";

    @Column(name = TITLE)
    private String title;

    @Column(name = HEADER)
    private String header;

    @Column(name = CORRECT_ANSWER)
    private String correctAnswer;
}
