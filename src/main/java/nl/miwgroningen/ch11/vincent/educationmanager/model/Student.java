package nl.miwgroningen.ch11.vincent.educationmanager.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * A student who can be part of a cohort and in that capacity can participate in an educational activity
 */

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;

    private String studentName;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    @Builder.Default private Set<Cohort> cohorts = new HashSet<>();
}
