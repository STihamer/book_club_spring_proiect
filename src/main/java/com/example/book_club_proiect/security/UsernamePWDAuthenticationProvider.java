package com.example.book_club_proiect.security;


import com.example.book_club_proiect.models.Roles;
import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePWDAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserRepository userRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("password" + password);
        System.out.println("username" + username);
        User user = userRepository.findUserByUsername(username);
        if (null != user && user.getUser_id() > 0 && password.matches(user.getUser_password())) {
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(), null, getGrantedAuthorities(user.getRoles())

            );

        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }



    private java.util.List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + roles.getRole_name()));
        return grantedAuthorityList;
    }

}
