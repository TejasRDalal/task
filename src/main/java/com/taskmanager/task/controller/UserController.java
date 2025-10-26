package com.taskmanager.task.controller;

import com.taskmanager.task.pojo.AuthCredentials;
import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.service.UserService;
import com.taskmanager.task.service.impl.AuthenticationService;
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

    @Autowired
    private AuthenticationService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentials req) {

        return ResponseEntity.ok(authService.authenticate(req));
    }

    @GetMapping("/getPassword")
    public ResponseEntity<?> getAllPasswords() {

        return ResponseEntity.ok(authService.getAllPassword());
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody Users users) {

        ResponseEntity<String> response =  userService.addUser(users);
        Map<String, String> res = new HashMap<>();
        res.put("message",response.getBody());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update")
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
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{userId}")
    public Users getUser(@PathVariable Long userId) {
        return userService.getUserById(userId).isPresent() ? userService.getUserById(userId).get() : null;
    }
}
