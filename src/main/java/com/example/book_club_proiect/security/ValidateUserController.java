package com.example.book_club_proiect.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/basicAuth")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ValidateUserController {
    private final JWTService jwtService;

    public ValidateUserController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @RequestMapping("validate")
    public Map<String, String> userIsValid() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        String name = currentUser.getUsername();
        String role = currentUser.getAuthorities().toArray()[0].toString().substring(5);

        String token = jwtService.generateToken(name, role);
        Map<String, String> results = new HashMap<>();
        results.put("result", token);
        return results;
    }
}

