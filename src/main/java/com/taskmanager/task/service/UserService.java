package com.taskmanager.task.service;

import com.taskmanager.task.pojo.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    ResponseEntity<String> addUser(Users users);
    void updateUser(Users users);
    void deleteUser(Long userId);
    Optional<Users> getUserById(Long userId);
    List<Users> getAllUsers();
}
