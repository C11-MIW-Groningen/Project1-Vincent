package nl.miwgroningen.ch11.vincent.educationmanager.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Cohort;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Student;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.CohortRepository;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Deal with Students
 */

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    final StudentRepository studentRepository;
    final CohortRepository cohortRepository;

    @GetMapping("/overview")
    public String showStudents(Model model) {
        model.addAttribute("allStudents", studentRepository.findAll());
        model.addAttribute("allCohorts", cohortRepository.findAll());
        return "student/overview";
    }

    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isPresent()) {
            for (Cohort cohort : student.get().getCohorts()) {
                cohort.removeStudent(student.get());
                cohortRepository.save(cohort);
            }
            studentRepository.deleteById(studentId);
        }

        return "redirect:/student/overview";
    }
}
