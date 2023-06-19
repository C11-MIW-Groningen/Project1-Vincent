package nl.miwgroningen.ch11.vincent.educationmanager.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch11.vincent.educationmanager.model.EducationalActivity;
import nl.miwgroningen.ch11.vincent.educationmanager.repository.EducationalActivityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/activity")
public class EducationalActivityController {
    private final EducationalActivityRepository educationalActivityRepository;

    @GetMapping({"/", "/overview"})
    public String getTopLevelOverview(Model model) {
        model.addAttribute("topLevelActivities",
                educationalActivityRepository.findAllBySuperActivityIsNull());
        return "activity/topLevelOverview";
    }

    @GetMapping("/new")
    public String showNewActivityForm(Model model) {
        model.addAttribute("activity", new EducationalActivity());
        List<EducationalActivity> allActivities = educationalActivityRepository.findAll();
        Collections.sort(allActivities);
        model.addAttribute("allActivities", allActivities);
        return "activity/form";
    }

    @PostMapping("/new")
    public String saveOrUpdateActivity(@ModelAttribute("activity") EducationalActivity activity, BindingResult result) {
        if (result.hasErrors()) {
            return "activity/form";
        }

        educationalActivityRepository.save(activity);

        return "redirect:/activity/overview";
    }
}
