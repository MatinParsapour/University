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
@Table(name = Questions.TABLE_NAME)
public class Questions extends BaseEntity<Long> {
    public static final String TABLE_NAME = "questions_table";

    @OneToOne
    private QuestionHeader questionHeader;

    @OneToOne
    private QuestionStatus questionStatus;
}
