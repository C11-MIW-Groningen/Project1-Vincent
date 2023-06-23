package nl.miwgroningen.ch11.vincent.educationmanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * An assessment is a way to assess student performance on an educationalActivity
 */

@Entity
@Getter @Setter
public abstract class Assessment {
    @Id @GeneratedValue
    private Long testId;

    @ManyToOne
    EducationalActivity testedActivity;
}
