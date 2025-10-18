package com.taskmanager.task.controller;

import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody Users users) {
        System.out.println("Received user data: " + users.toString());
        userService.addUser(users);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User added successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody Users users) {
        userService.addUser(users);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{userId}")
    public Users getUser(@PathVariable Integer userId) {
        return userService.getUserById(userId).isPresent() ? userService.getUserById(userId).get() : null;
    }
}
