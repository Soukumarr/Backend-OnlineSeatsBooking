package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.model.User;
import com.example.OnlineSeatBook.repository.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {

        return userRepository.save(user);
    }
    //getallusers
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
