package com.taskmanager.task.service.impl;

import com.taskmanager.task.pojo.AuthCredentials;
import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.repositories.AuthCredentialsRepository;
import com.taskmanager.task.repositories.RoleRepository;
import com.taskmanager.task.repositories.UserRepository;
import com.taskmanager.task.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthCredentialsRepository authCredentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> addUser(Users users) {


        // 1. validate username uniqueness
    if (authCredentialsRepository.findByUsername(users.getUsername()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    }

    // 2. resolve or create Users row
    if (users.getUserId() != null) {
        users = userRepository.findById(users.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId not found"));
    } else {
        users = userRepository.save(users);
    }

    // 3. create AuthCredentials linked to Users
    AuthCredentials creds = new AuthCredentials();
    creds.setUser(users);
    creds.setUsername(users.getUsername());
    creds.setPassword(passwordEncoder.encode(users.getPassword()));
    authCredentialsRepository.save(creds);

    // 4. optional: return generated JWT so client is logged in immediately
    /*String token = jwtUtil.generateTokenFromUsername(users.getUsername());
    return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));*/

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username added successfully");
    }

    @Override
    public void updateUser(Users users) {
        userRepository.save(users);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public Optional<Users> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findByFlagTrue();
    }
}
