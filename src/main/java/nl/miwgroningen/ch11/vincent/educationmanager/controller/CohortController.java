package nl.miwgroningen.ch11.vincent.educationmanager.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.CohortRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Deal with Cohorts
 */

@Controller
@RequestMapping("/cohort")
@RequiredArgsConstructor
public class CohortController {
    final CohortRepository cohortRepository;

    @GetMapping("/overview")
    public String showCohortOverview(Model model) {
        model.addAttribute("allCohorts", cohortRepository.findAll());
        return "cohort/overview";
    }

    @GetMapping("/delete/{cohortId}")
    public String deleteCohort(@PathVariable("cohortId") Long cohortId) {
        cohortRepository.deleteById(cohortId);
        return "redirect:/student/overview";
    }
}
