package com.ulco.dashboard.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "test message");
        return "dashboard";
    }

}
