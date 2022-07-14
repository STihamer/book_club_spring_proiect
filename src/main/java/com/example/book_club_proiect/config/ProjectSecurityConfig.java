package com.example.book_club_proiect.config;


import com.example.book_club_proiect.security.UsernamePwdAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan("com.example.book_club_proiect.config")
public class ProjectSecurityConfig extends

        WebSecurityConfigurerAdapter {
    @Autowired
    private UsernamePwdAuthenticationProvider authProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .mvcMatchers(HttpMethod.POST,"/users").authenticated()
                .mvcMatchers( "/users/**").hasAuthority("ROLE_admin")
                .mvcMatchers(HttpMethod.POST, "/books").authenticated()
                .mvcMatchers( "/books/**").hasAuthority("ROLE_admin")
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
}
