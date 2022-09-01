package com.example.book_club_proiect.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

@Controller
public class SwaggerConfig {
    //public static final Contact DEFAULT_CONTACT
    public static final Contact DEFAULT_CONTACT = new Contact("Sebok Tihamer", "", "tihamer@gmail.com");
public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Book club project API Title", "Book club project API " +
        "documentation", "1" +
        ".0",
        "urn:tos",
        DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());

private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(List.of("application/json"));
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
