package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = QuestionHeader.TABLE_NAME)
public class QuestionHeader extends BaseEntity<Long> {
    public static final String TABLE_NAME = "question_header_table";

    @OneToOne
    private MultipleChoices multipleChoices;

    @OneToOne
    private Descriptive descriptive;

}
