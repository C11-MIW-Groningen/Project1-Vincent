package nl.miwgroningen.ch11.vincent.educationmanager.dto;

import lombok.Builder;
import lombok.Data;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Cohort;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Student;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Capture the enrolling of a student to a cohort
 */

@Data @Builder
public class EnrollmentDTO {
    private Cohort cohort;
    private Student student;
}
