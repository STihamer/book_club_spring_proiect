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
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "api/basicAuth/**").permitAll()
                .antMatchers(HttpMethod.GET, "api/users/currentUserRole").permitAll()
                .antMatchers(HttpMethod.GET, "api/basicAuth/**").hasAnyRole("admin", "user")
                .and().httpBasic()
                .authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint());

        httpSecurity
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("admin", "user")
                .antMatchers(HttpMethod.GET, "/api/rentingTables").hasAnyRole("admin", "user")
                .antMatchers(HttpMethod.GET, "/api/rentingTables/**").hasAnyRole("admin", "user")
                .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/rentingTables/**").hasAnyRole("admin", "user")
                .antMatchers(HttpMethod.POST, "/api/books").hasRole("admin")
                .antMatchers(HttpMethod.POST, "/api/bookOwners").hasAnyRole("admin", "user")
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("admin")
                .antMatchers(HttpMethod.DELETE, "/api/rentingTables/**").hasAnyRole("admin", "user")
                .antMatchers(HttpMethod.PUT, "/api/books/**").hasAnyRole("admin", "user")

                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));

    }
}
