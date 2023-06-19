package nl.miwgroningen.ch11.vincent.educationmanager.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Cohort;
import nl.miwgroningen.ch11.vincent.educationmanager.model.EducationalActivity;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Student;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.CohortRepository;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.EducationalActivityRepository;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Endpoints that should ONLY be available to admins
 */

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CohortRepository cohortRepository;
    private final EducationalActivityRepository educationalActivityRepository;
    private final StudentRepository studentRepository;

    @GetMapping("/seed")
    public String seedData() {
        EducationalActivity activity = EducationalActivity.builder().name("Software Engineering").build();
        EducationalActivity activity2 = EducationalActivity.builder().name("Functioneel Beheer").build();

        educationalActivityRepository.save(activity);
        educationalActivityRepository.save(activity2);

        Cohort cohort1 = Cohort.builder().name("1").educationalActivity(activity).build();
        Cohort cohort2 = Cohort.builder().name("2").educationalActivity(activity2).build();
        Cohort cohort3 = Cohort.builder().name("3").educationalActivity(activity).build();

        cohortRepository.save(cohort1);
        cohortRepository.save(cohort2);
        cohortRepository.save(cohort3);

        Student student1 = Student.builder().studentName("Student 1").build();
        student1.getCohorts().add(cohort1);
        student1.getCohorts().add(cohort2);
        Student student2 = Student.builder().studentName("Student 2").build();
        student2.getCohorts().add(cohort1);
        student2.getCohorts().add(cohort3);
        Student student3 = Student.builder().studentName("Student 3").build();
        student3.getCohorts().add(cohort2);
        student3.getCohorts().add(cohort3);

        return "redirect:/";
    }

}
