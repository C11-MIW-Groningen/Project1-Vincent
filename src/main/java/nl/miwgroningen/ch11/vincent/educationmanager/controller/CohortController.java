package nl.miwgroningen.ch11.vincent.educationmanager.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch11.vincent.educationmanager.dto.EnrollmentDTO;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Cohort;
import nl.miwgroningen.ch11.vincent.educationmanager.model.Student;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.CohortRepository;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Deal with Cohorts
 */

@Controller
@RequestMapping("/cohort")
@RequiredArgsConstructor
public class CohortController {
    private final CohortRepository cohortRepository;
    private final StudentRepository studentRepository;

    @GetMapping("/overview")
    public String showCohortOverview(Model model) {
        model.addAttribute("allCohorts", cohortRepository.findAll());
        return "cohort/overview";
    }

    @GetMapping("/detail/{cohortName}")
    public String showCohortDetails(@PathVariable("cohortName") String cohortName, Model model) {
        Optional<Cohort> cohortOptional = cohortRepository.findByName(cohortName);

        if (cohortOptional.isPresent()) {
            model.addAttribute("cohort", cohortOptional.get());
            List<Student> unenrolledStudents = studentRepository.findAll();
            unenrolledStudents.removeAll(cohortOptional.get().getStudents());
            model.addAttribute("allUnenrolledStudents", unenrolledStudents);
            model.addAttribute("enrollment", EnrollmentDTO.builder().cohort(cohortOptional.get()).build());
            return "cohort/detail";
        }

        return "redirect:/cohort/overview";
    }

    @GetMapping("/delete/{cohortId}")
    public String deleteCohort(@PathVariable("cohortId") Long cohortId) {
        cohortRepository.deleteById(cohortId);
        return "redirect:/student/overview";
    }

    @PostMapping("/enrollstudent")
    public String enrollStudent(@ModelAttribute("enrollment") EnrollmentDTO enrollment, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/cohort/overview";
        }

        enrollment.getCohort().addStudent(enrollment.getStudent());
        cohortRepository.save(enrollment.getCohort());

        return "redirect:/cohort/detail/" + enrollment.getCohort().getName();
    }

    @GetMapping("/unenroll/{cohortId}/{studentId}")
    public String unenrollStudent(@PathVariable("cohortId") Long cohortId, @PathVariable("studentId") Long studentId) {
        Optional<Cohort> cohortOptional = cohortRepository.findById(cohortId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (cohortOptional.isEmpty() || studentOptional.isEmpty()) {
            return "redirect:/cohort/overview";
        }

        cohortOptional.get().removeStudent(studentOptional.get());
        cohortRepository.save(cohortOptional.get());

        return "redirect:/cohort/detail/" + cohortOptional.get().getName();
    }
}
