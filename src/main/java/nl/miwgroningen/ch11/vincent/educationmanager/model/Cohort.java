package nl.miwgroningen.ch11.vincent.educationmanager.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Represents a group of Students that enroll to an education activity together
 */

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Cohort {
    @Id @GeneratedValue
    private Long cohortId;

    private String name;

    @ManyToOne
    private EducationalActivity educationalActivity;

    @ManyToMany
    @Builder.Default private Set<Student> students = new HashSet<>();
}
