package ir.maktab.University.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User{
    private static final String STATUS = "status";

    @Column(name = STATUS)
    private String status;

    @OneToOne(mappedBy = "teacher")
    private Course course;
}
