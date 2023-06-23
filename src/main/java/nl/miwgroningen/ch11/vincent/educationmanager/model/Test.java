package nl.miwgroningen.ch11.vincent.educationmanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * A form of assessment where something is submitted which is then graded and provided with feedback by a teacher
 */

@Entity
@Getter @Setter
public class Test extends Assessment {
    @OneToMany(mappedBy = "test")
    List<Question> questions;

    public int getTotalAvailablePoints() {
        return questions.stream().mapToInt(Question::getPointsPossible).sum();
    }
}
