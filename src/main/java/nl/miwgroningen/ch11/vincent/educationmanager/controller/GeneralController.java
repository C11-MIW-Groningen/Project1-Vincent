package nl.miwgroningen.ch11.vincent.educationmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Handle general, high level request
 */

@Controller
public class GeneralController {

    @GetMapping("/")
    private String showHomePage() {
        return "redirect:/activity/overview";
    }
}
