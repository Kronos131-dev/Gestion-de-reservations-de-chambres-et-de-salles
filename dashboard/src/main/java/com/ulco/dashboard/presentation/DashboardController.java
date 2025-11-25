package com.ulco.dashboard.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/")
    public String defaultLink() {
        return "rooms";
    }
    @GetMapping("/rooms")
    public String rooms() {
        return "rooms";
    }
    @GetMapping("/prestation")
    public String prestations() {
        return "prestation";
    }
    @GetMapping("/users")
    public String users(Model model) {
        return "users";
    }

}
