package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.services.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@RestController
@RequestMapping("users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsersController {
    @Autowired
    private final UserService userService;


    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return userService.getById(id).isPresent() ? userService.getById(id).get() : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping
    public User createSession(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
