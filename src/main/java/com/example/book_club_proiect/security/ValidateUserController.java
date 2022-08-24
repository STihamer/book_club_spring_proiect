package com.example.book_club_proiect.security;

import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/basicAuth")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ValidateUserController {
    private final JWTService jwtService;

    private final UserRepository userRepository;

    public ValidateUserController(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @RequestMapping("validate")
    public Map<String, String> userIsValid() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findUserByUsername(auth.getName());
        String name = currentUser.getUsername();
        String role = auth.getAuthorities().toArray()[0].toString().substring(5);
        String token = jwtService.generateToken(name, role);
        Map<String, String> results = new HashMap<>();
        results.put("result", token);
        return results;
    }
}

