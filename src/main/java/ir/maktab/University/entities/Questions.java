package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Questions.TABLE_NAME)
public class Questions extends BaseEntity<Long> {
    public static final String TABLE_NAME = "questions_table";

    @OneToMany(mappedBy = "questions")
    private List<Question> questionList;

    @OneToMany(mappedBy = "questions")
    private List<Grade> gradeList;
}
