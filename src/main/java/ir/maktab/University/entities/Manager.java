package ir.maktab.University.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends User {

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private List<Course> courses;
}
