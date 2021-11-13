package ir.maktab.University.entities;

import ir.maktab.University.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = QuestionsBank.TABLE_NAME)
public class QuestionsBank extends BaseEntity<Long> {
    public static final String TABLE_NAME = "questions_bank_table";

    @OneToMany(fetch = FetchType.EAGER)
    private Set<QuestionHeader> questionHeaders = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();
}
