package com.taskmanager.task.service;

import com.taskmanager.task.pojo.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(Users users);
    void updateUser(Users users);
    void deleteUser(Integer userId);
    Optional<Users> getUserById(Integer userId);
    List<Users> getAllUsers();
}
