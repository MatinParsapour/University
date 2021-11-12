package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = MultipleChoices.TABLE_NAME)
public class MultipleChoices extends BaseEntity<Long> {
    public static final String TABLE_NAME = "multiple_choices_table";
    private static final String TITLE = "title";
    private static final String HEADER = "header";
    private static final String CORRECT_ANSWER = "correct_answer";

    @Column(name = TITLE)
    private String title;

    @Column(name = HEADER)
    private String header;

    @ElementCollection
    private  List<Options> options;

    @Column(name = CORRECT_ANSWER)
    private String getCorrectAnswer;
}
