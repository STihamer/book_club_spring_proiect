package com.example.book_club_proiect.security;

import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public Map<String, String> userIsValid(HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findUserByUsername(auth.getName());
        String name = currentUser.getUsername();
        String role = auth.getAuthorities().toArray()[0].toString().substring(5);
        String token = jwtService.generateToken(name, role);
        Map<String, String> results = new HashMap<>();
        results.put("result", "ok");

        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/api");
        cookie.setHttpOnly(true);
        // TODO: When in production must do cookie.setSecure(true);
        cookie.setMaxAge(1800);
        response.addCookie(cookie);

        return results;
    }
}

