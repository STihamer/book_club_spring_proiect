package com.example.book_club_proiect.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@ComponentScan("com.example.book_club_proiect.models")

public class ProjectSecurityConfig extends

        WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .mvcMatchers( "/api/users").permitAll()
                .mvcMatchers("/api/users/**").permitAll()       //hasAuthority("ROLE_admin")
                .mvcMatchers("/api/books").permitAll() //authenticated()
                .mvcMatchers("/api/books/**").permitAll()//hasAuthority("ROLE_admin")
                .mvcMatchers("/api/myListings/**").permitAll()//authenticated()
                .mvcMatchers("/api/waitingLists/**").permitAll()//authenticated()
                .mvcMatchers("/api/rentingTables/**").permitAll()//authenticated()
                .mvcMatchers("/api/booksNonRented").permitAll() //authenticated()
                .mvcMatchers("/api/userTitleAndReturnDate").permitAll()//authenticated()
                .mvcMatchers("/api/oneBookByTitle").permitAll()//authenticated()
                .mvcMatchers("/api/bookAvailabilityByAuthorOrTitle").permitAll()//authenticated()
                .mvcMatchers("/api/firstNameAndEmail").permitAll()//authenticated()
                .mvcMatchers("/api/waitingPersonsAndBookTitle").permitAll()//authenticated()
                .mvcMatchers("/api/myListingsByUser").permitAll()//authenticated()
                .mvcMatchers("/api/login").permitAll()
                .and().formLogin()
                .defaultSuccessUrl("/api/users")
                .failureUrl("/login?error = true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout = true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
