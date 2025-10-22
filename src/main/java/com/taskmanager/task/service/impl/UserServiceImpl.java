package com.taskmanager.task.service.impl;

import com.taskmanager.task.pojo.Role;
import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.repositories.RoleRepository;
import com.taskmanager.task.repositories.UserRepository;
import com.taskmanager.task.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addUser(Users users) {
        int roleId = users.getRole().getRoleId();

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));

        users.setRole(role); // attach managed Role entity
        userRepository.save(users);
    }

    @Override
    public void updateUser(Users users) {
        userRepository.save(users);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public Optional<Users> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findByFlagTrue();
    }
}
