package com.taskmanager.task.controller;

import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody Users users) {
        userService.addUser(users);
        return "User added successfully";
    }

    @GetMapping("/getAll")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return "User deleted successfully";
    }

    @GetMapping("/get/{userId}")
    public Users getRole(@PathVariable Integer userId) {
        return userService.getUserById(userId).isPresent() ? userService.getUserById(userId).get() : null;
    }
}
