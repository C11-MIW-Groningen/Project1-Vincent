package nl.miwgroningen.ch11.vincent.educationmanager.controller;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Cohort;
import nl.miwgroningen.ch11.vincent.educationmanager.model.EducationalActivity;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Student;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.CohortRepository;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.EducationalActivityRepository;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

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

    private final Faker faker = new Faker(new Locale("nl"));

    @GetMapping("/seed")
    public String seedData() {
        cohortRepository.deleteAll();
        educationalActivityRepository.deleteAll();
        studentRepository.deleteAll();

        educationalActivityRepository.saveAll(generateEducationalActivities());
        cohortRepository.saveAll(generateCohorts(11));
        studentRepository.saveAll(generateStudentsPerCohort());

        return "redirect:/";
    }

    public Set<EducationalActivity> generateEducationalActivities() {
        Set<EducationalActivity> activities = new HashSet<>();

        activities.add(generateSoftwareEngineering());
        activities.add(generateFunctioneelBeheer());

        return activities;
    }

    public EducationalActivity generateSoftwareEngineering() {
        EducationalActivity softwareEngineering = EducationalActivity.builder().name("Software Engineering").build();

        EducationalActivity fase1 = EducationalActivity.builder().name("Fase 1").superActivity(softwareEngineering).build();
        softwareEngineering.getSubActivities().add(fase1);
        EducationalActivity programming = EducationalActivity.builder().name("Programming").superActivity(fase1).build();
        fase1.getSubActivities().add(programming);
        EducationalActivity databases = EducationalActivity.builder().name("Databases").superActivity(fase1).build();
        fase1.getSubActivities().add(databases);
        EducationalActivity oop = EducationalActivity.builder().name("OOP").superActivity(fase1).build();
        fase1.getSubActivities().add(oop);

        EducationalActivity fase2 = EducationalActivity.builder().name("Fase 2").superActivity(softwareEngineering).build();
        softwareEngineering.getSubActivities().add(fase2);
        EducationalActivity intermediate = EducationalActivity.builder().name("Intermediate Programming").superActivity(fase2).build();
        fase2.getSubActivities().add(intermediate);
        EducationalActivity project1 = EducationalActivity.builder().name("Project 1").superActivity(fase2).build();
        fase2.getSubActivities().add(project1);

        EducationalActivity fase3 = EducationalActivity.builder().name("Fase 3").superActivity(softwareEngineering).build();
        softwareEngineering.getSubActivities().add(fase3);
        EducationalActivity advanced = EducationalActivity.builder().name("Advanced Programming").superActivity(fase3).build();
        fase3.getSubActivities().add(advanced);
        EducationalActivity project2 = EducationalActivity.builder().name("Project 2").superActivity(fase3).build();
        fase3.getSubActivities().add(project2);

        return softwareEngineering;
    }

    public EducationalActivity generateFunctioneelBeheer() {
        return EducationalActivity.builder().name("Functioneel Beheer").build();
    }

    public Set<Cohort> generateCohorts(int numberOfCohorts) {
        Set<Cohort> cohorts = new HashSet<>();
        List<EducationalActivity> topLevelActivities = educationalActivityRepository.findAllBySuperActivityIsNull();

        for (int cohort = 0; cohort < numberOfCohorts; cohort++) {
            EducationalActivity activity = topLevelActivities.get(cohort % topLevelActivities.size());
            cohorts.add(Cohort.builder().educationalActivity(activity).name(faker.coffee().blendName()).build());
        }

        return cohorts;
    }

    public Set<Student> generateStudentsPerCohort() {
        return generateStudentsPerCohort(5, 24);
    }

    public Set<Student> generateStudentsPerCohort(int minimumStudent, int maxStudents) {
        Set<Student> students = new HashSet<>();
        List<Cohort> cohorts = cohortRepository.findAll();

        for (Cohort cohort : cohorts) {
            for (int i = 0; i < faker.number().numberBetween(minimumStudent, maxStudents); i++) {
                Student student = Student.builder().studentName(faker.name().fullName()).build();
                student.getCohorts().add(cohort);
                cohort.addStudent(student);
                students.add(student);
            }
        }

        return students;
    }

}
