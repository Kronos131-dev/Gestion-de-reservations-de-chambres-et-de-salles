package com.ulco.dashboard.presentation;

import com.ulco.dashboard.business.UserBusiness;
import com.ulco.dashboard.persistence.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final UserBusiness userBusiness;

    public UserController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @GetMapping("/api/getUsers")
    @ResponseBody
    public Map<String, List<User>> getAllUsers(Model model) {
        List<User> users = userBusiness.getAllUsers();
        return Map.of("value", users);
    }




}
