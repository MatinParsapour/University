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
@Table(name = Question.TABLE_NAME)
public class Question extends BaseEntity<Long> {
    public static final String TABLE_NAME = "question_table";

    @OneToOne
    private QuestionHeader questionHeader;

    @ManyToMany
    private List<QuestionAnswer> questionAnswerList;

    @ManyToOne
    private Questions questions;
}
