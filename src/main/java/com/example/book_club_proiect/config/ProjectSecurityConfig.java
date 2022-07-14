package com.example.book_club_proiect.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/home").permitAll()
                .mvcMatchers(HttpMethod.POST, "/users").authenticated()
                .mvcMatchers(HttpMethod.POST, "/users/**").denyAll()
                .mvcMatchers(HttpMethod.POST, "/books").authenticated()
                .mvcMatchers(HttpMethod.POST, "/books/**").denyAll()
                .mvcMatchers("/my_listing").authenticated()
                .mvcMatchers("/waiting_list/**").authenticated()
                .mvcMatchers("/renting_table/**").authenticated()
                .mvcMatchers("/booksNonRented").authenticated()
                .mvcMatchers("/UserTitleAndReturnDate").authenticated()
                .mvcMatchers("/OneBookByTitle").authenticated()
                .mvcMatchers("/bookAvailabilityByAuthorOrTitle").authenticated()
                .mvcMatchers("/first_name_email").authenticated()
                .mvcMatchers("/waitingPersonsAndBookTitle").authenticated()
                .mvcMatchers("/myListingByUser").authenticated()
                .mvcMatchers("/login").authenticated()
                .and().formLogin()
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error = true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout = true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("12345").roles("User")
                .and()
                .withUser("admin").password("54321").roles("USER", "ADMIN")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
