package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.model.User;
import com.example.OnlineSeatBook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    public User registerUser(User user) {

        return userRepository.save(user);
    }
    public User resetPassword(String email, String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return user;
        }
        return null;
    }
    //getallusers
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
