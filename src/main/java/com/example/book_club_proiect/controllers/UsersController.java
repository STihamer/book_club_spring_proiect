package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.UserRepository;
import com.example.book_club_proiect.services.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsersController {
    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    public UsersController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {

        return userService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return userService.getById(id).isPresent() ? userService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping
    public User createUser( @Valid @RequestParam String first_name,
                            @Valid @RequestParam String last_name,
                           @RequestParam Integer user_age,
                           @RequestParam String username,
                           @Valid @RequestParam String user_email,
                           @RequestParam String user_password,
                           @RequestParam Long role_id) {
        return userService.createUser(
                first_name, last_name, user_age, username, user_email, user_password, role_id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {

        return ResponseEntity.ok(userService.updateUser(id, user));

    }

    @GetMapping("/currentUserRole")
    public Map<String, String> getCurrentUsersRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Collection<GrantedAuthority> roles =
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = "";
        if (roles.size() > 0) {
            GrantedAuthority ga = roles.iterator().next();
            role = ga.getAuthority().substring(5);
        }
        Map<String, String> results = new HashMap<>();
        results.put("role", role);
        return results;
    }

    @GetMapping("/currentUserId")
    public Map<String, Long> getCurrentUsersId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findUserByUsername(auth.getName());
        Long id = currentUser.getUser_id();
        Map<String, Long> results = new HashMap<>();
        results.put("id", id);
        return results;
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/api");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        SecurityContextHolder.getContext().setAuthentication(null);
        return "";
    }
}
