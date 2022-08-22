package com.example.book_club_proiect.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/basicAuth")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ValidateUserController {
    @RequestMapping("validate")
    public String userIsValid() {
        return "{\"result\": \"ok\"}";
    }
}

