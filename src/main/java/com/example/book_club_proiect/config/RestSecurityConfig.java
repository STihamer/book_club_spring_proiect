package com.example.book_club_proiect.config;

import com.example.book_club_proiect.security.JWTAuthorizationFilter;
import com.example.book_club_proiect.security.UsernamePWDAuthenticationProvider;
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
    UsernamePWDAuthenticationProvider usernamePWDAuthenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePWDAuthenticationProvider);
        //Todo: this password should be encoded
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "api/basicAuth/**").permitAll()
                .antMatchers("api/basicAuth/**").hasAnyRole("admin", "user")

                .and().httpBasic();

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("admin", "user")
                .antMatchers(HttpMethod.GET, "/api/rentingTables").hasAnyRole("admin", "user")
                .antMatchers(HttpMethod.GET, "/api/rentingTables/**").hasAnyRole("admin", "user")
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));

    }
}
