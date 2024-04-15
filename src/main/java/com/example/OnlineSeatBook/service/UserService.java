package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.model.User;
import com.example.OnlineSeatBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findByEmail(email);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
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
    public User updateUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setName(user.getName());
            existingUser.setPhone(user.getPhone());
            existingUser.setAddress(user.getAddress());
            return userRepository.save(existingUser);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
//    public String addUser(User userInfo) {
//        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
//        userRepository.save(userInfo);
//        return "User Added Successfully";
//    }
    //getallusers
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
