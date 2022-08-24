package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.UserRepository;
import com.example.book_club_proiect.services.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User createUser(@RequestParam String first_name,
                           @RequestParam String last_name,
                           @RequestParam Integer user_age,
                           @RequestParam String username,
                           @RequestParam String user_email,
                           @RequestParam String user_password,
                           @RequestParam Long role_id) {
        return userService.createUser(
                first_name, last_name, user_age, username, user_email, user_password, role_id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {

            return ResponseEntity.ok(userService.updateUser(id, user));

    }
}
