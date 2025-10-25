package com.taskmanager.task.controller;

import com.taskmanager.task.pojo.AuthCredentials;
import com.taskmanager.task.pojo.AuthResponse;
import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.security.jwt.JwtUtils;
import com.taskmanager.task.service.UserDetailsService;
import com.taskmanager.task.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentials req) {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
            String jwt = jwtUtil.generateTokenFromUsername(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
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
