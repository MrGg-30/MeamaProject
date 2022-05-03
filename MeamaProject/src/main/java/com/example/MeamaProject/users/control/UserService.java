package com.example.MeamaProject.users.control;

import com.example.MeamaProject.users.entity.User;
import com.example.MeamaProject.users.repository.UserRepository;
import com.example.MeamaProject.exception.SecurityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public Long createUser(User user){
        User createdUser;
        try {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            createdUser = userRepository.saveAndFlush(user);
        } catch (Exception ex) {
            throw new SecurityViolationException();
        }
        return createdUser.getId();
    }

    public User findUser(String name){
        return userRepository.findByUsername(name);
    }
}
