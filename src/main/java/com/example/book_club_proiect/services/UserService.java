package com.example.book_club_proiect.services;


import com.example.book_club_proiect.dto.UserDTO;
import com.example.book_club_proiect.mapper.UserMapper;
import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.RolesRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository, RolesRepository rolesRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }


    public List<UserDTO> getAll() {

        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public Optional<UserDTO> getById(Long id) {
        User user = userRepository.findById(id).get();
        return Optional.of(userMapper.toDto(user));
    }

    public User createUser(String first_name, String last_name, Integer user_age, String username, String user_email,
                           String user_password, Long role_id) {
        List<User> users = userRepository.findUsersByUsername(username).stream().toList();
        if(users.size()>0){

                throw new UnsupportedOperationException(
                        "An user with this username  : " + username + " already exists");
        }
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

    public User updateUser(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setFirst_name(userDto.getFirstName());
        existingUser.setLast_name(userDto.getLastName());
        existingUser.setUser_age(userDto.getUserAge());
        existingUser.setUsername(userDto.getUsername());
        existingUser.setUser_email(userDto.getUserEmail());
        existingUser.setRole_id(userDto.getRoleId());
        existingUser.setRoles(rolesRepository.findById(userDto.getRoleId()).get());
        return userRepository.saveAndFlush(existingUser);
    }
}
