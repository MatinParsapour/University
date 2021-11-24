package ir.maktab.University.entities.dto.extra;

import ir.maktab.University.entities.Options;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class QuestionHeaderDTO {

    private long id;

    private String header;

    private String type;

    @ElementCollection
    private Set<Options> options = new HashSet<>();
}
