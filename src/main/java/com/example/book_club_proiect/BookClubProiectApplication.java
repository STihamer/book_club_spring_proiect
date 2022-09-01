package com.example.book_club_proiect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan("com.example.book_club_proiect.models")
public class BookClubProiectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookClubProiectApplication.class, args);
    }

}
