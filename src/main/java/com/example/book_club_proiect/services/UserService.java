package com.example.book_club_proiect.services;

import com.example.book_club_proiect.models.RentingTable;
import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.RolesRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
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
        user.setUser_password(passwordEncoder.encode(user_password));
        user.setRole_id(role_id);
        user.setRoles(rolesRepository.findById(role_id).get());
        return userRepository.saveAndFlush(user);

    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User existingSession = userRepository.findById(id).get();
        BeanUtils.copyProperties(user, existingSession, "id");
        return userRepository.saveAndFlush(existingSession);
    }




}
