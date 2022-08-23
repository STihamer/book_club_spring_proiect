package com.example.book_club_proiect.config;

import com.example.book_club_proiect.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("matt").password("{noop}secret").authorities("ROLE_ADMIN")
                .and()
                .withUser("jane").password("{noop}secret").authorities("ROLE_USER");
        //Todo: this password should be encoded
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/basicAuth/**").permitAll()
                .antMatchers("/api/basicAuth/**").hasAnyRole("ADMIN", "USER")

                .and().httpBasic();
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/books").permitAll()
                .antMatchers(HttpMethod.GET, "api/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/users/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));

    }
}
