package nl.miwgroningen.ch11.vincent.educationmanager.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * An educational activity represents some unit of learning
 * Educational activities can be combined into a collection that itself is an educational activity
 */

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class EducationalActivity implements Comparable<EducationalActivity> {
    @Id
    @GeneratedValue
    private Long activityId;

    private String name;

    @ManyToOne
    private EducationalActivity superActivity;

    @OneToMany(mappedBy = "superActivity", cascade = CascadeType.PERSIST)
    @Builder.Default private List<EducationalActivity> subActivities = new ArrayList<>();

    @OneToMany(mappedBy = "testedActivity")
    private Set<Assessment> assessedUsing;

    public int getDepth() {
        if (superActivity == null) {
            return 0;
        }
        return 1 + superActivity.getDepth();
    }

    @Override
    public int compareTo(EducationalActivity otherActivity) {
        if (this.getDepth() != otherActivity.getDepth()) {
            return this.getDepth() - otherActivity.getDepth();
        } else if (this.superActivity != otherActivity.superActivity && superActivity != null) {
            return superActivity.compareTo(otherActivity.superActivity);
        }
        return this.name.compareTo(otherActivity.name);
    }

    @Override
    public String toString() {
        String superActivityString = "";

        if (superActivity != null) {
            superActivityString = superActivity.toString() + " - ";
        }

        return superActivityString + name;
    }
}
