package com.example.book_club_proiect.services;

import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {

        return userRepository.findById(id);
    }

    public User createUser(final User user) {
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
