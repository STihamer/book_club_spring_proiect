package com.example.book_club_proiect.config;


import com.example.book_club_proiect.security.UsernamePwdAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
                .mvcMatchers( "/users").permitAll()
                .mvcMatchers("/users/**").hasAuthority("ROLE_admin")
                .mvcMatchers("/books").authenticated()
                .mvcMatchers("/books/**").hasAuthority("ROLE_admin")
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
                .mvcMatchers("/login").permitAll()
                .and().formLogin()
                .defaultSuccessUrl("/users")
                .failureUrl("/login?error = true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout = true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
