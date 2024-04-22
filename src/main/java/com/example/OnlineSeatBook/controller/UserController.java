package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.model.AuthRequest;
import com.example.OnlineSeatBook.model.User;
import com.example.OnlineSeatBook.service.JwtService;
import com.example.OnlineSeatBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/users/signin")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        Optional<User> foundUser = Optional.ofNullable(userService.findByEmail(user.getEmail()));
//
//        if (foundUser.isPresent()) {
//            if (foundUser.get().getPassword().equals(user.getPassword())) {
//                Map<String, Object> claims = new HashMap<>();
//                String roles = foundUser.get().getRole();
//                int u_id = foundUser.get().getId();
//                claims.put("id",u_id);
//                claims.put("roles", roles);
//                String token = jwtService.createToken(claims, user.getEmail());
//                return new ResponseEntity<>(token, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Password does not match", HttpStatus.UNAUTHORIZED);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }

    @PostMapping("/users/signin")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        //Optional<User> foundUser = Optional.ofNullable(userService.findByEmail(user.getEmail()));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
                Map<String, Object> claims = new HashMap<>();
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse(null);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User userFromDb = userService.findByEmail(userDetails.getUsername());
            int u_id = userFromDb.getId();
                claims.put("id",u_id);
                claims.put("roles", role);
                String token = jwtService.createToken(claims, user.getEmail());
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    //getallusers
    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/auth/user/resetpassword")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request,@RequestHeader("Authorization") String token) {

        String newPassword = request.get("newPassword");

        String tokenEmail = null;
        if (token != null) {
            tokenEmail = jwtService.extractUsername(token.replace("Bearer ", ""));
        }
        if (tokenEmail != null) {
            User user = userService.resetPassword(tokenEmail, newPassword);

            if (user != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }

    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/auth/user/editProfile")
    public ResponseEntity<?> editProfile(@RequestBody User updatedUserDetails, @RequestHeader("Authorization") String token) {
        String email = jwtService.extractUsername(token.replace("Bearer ", ""));
        Optional<User> userOptional = Optional.ofNullable(userService.findByEmail(email));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUserDetails.getName());
            user.setPhone(updatedUserDetails.getPhone());
            user.setAddress(updatedUserDetails.getAddress());
            userService.updateUser(user);

            // Invalidate the token to log the user out
            // This can be done by adding the token to a token blacklist,
            // or by changing the user's sign-in credentials (e.g., changing a last sign-in timestamp)

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/auth/user/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String token) {
        String tokenEmail = jwtService.extractUsername(token.replace("Bearer ", ""));
        User user = userService.findByEmail(tokenEmail);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            //added
            //UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
            return jwtService.generateToken(authRequest.getUsername());
            //return jwtService.generateToken(userDetails);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}