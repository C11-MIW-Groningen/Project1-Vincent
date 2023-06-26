package nl.miwgroningen.ch11.vincent.educationmanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * A seperatly assessed part of a test
 */

@Entity
@Getter @Setter
public class Question {
    @Id @GeneratedValue
    private Long questionId;

    @ManyToOne
    private Test test;

    private String questionText;
    private String assessmentCriteria;
    private Integer pointsPossible;

    public void setPointsPossible(Integer pointsPossible) {
        if (pointsPossible < 0) {
            throw new IllegalArgumentException("A question cannot have negative points");
        }
        this.pointsPossible = pointsPossible;
    }
}
