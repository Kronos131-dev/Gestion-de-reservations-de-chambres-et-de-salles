package com.ulco.dashboard.business;

import com.ulco.dashboard.persistence.User;
import com.ulco.dashboard.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusiness {
    private final UserRepository userRepository;

    public UserBusiness(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
