package com.example.book_club_proiect.services;


import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.RolesRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RolesRepository rolesRepository;

    public UserService(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {

        return userRepository.findById(id);
    }

    public User createUser(String first_name, String last_name, Integer user_age, String username, String user_email,
     String user_password, Long role_id) {
        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setUser_age(user_age);
        user.setUsername(username);
        user.setUser_email(user_email);
        user.setUser_password(user_password);
        user.setRole_id(role_id);
        user.setRoles(rolesRepository.findById(role_id).get());
        return userRepository.saveAndFlush(user);

    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setFirst_name(user.getFirst_name());
        existingUser.setLast_name(user.getLast_name());;
        existingUser.setUser_age(user.getUser_age());
        existingUser.setUsername(user.getUsername());
        existingUser.setUser_email(user.getUser_email());
        existingUser.setUser_password(user.getUser_password());
        existingUser.setRole_id(user.getRole_id());
        existingUser.setRoles(rolesRepository.findById(user.getRole_id()).get());
        return userRepository.saveAndFlush(existingUser);
    }
}
