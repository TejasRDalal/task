package com.taskmanager.task.service.impl;

import com.taskmanager.task.pojo.AuthCredentials;
import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.repositories.AuthCredentialsRepository;
import com.taskmanager.task.service.IAuthCredentials;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthCredentialsImpl implements IAuthCredentials {

    @Autowired
    private AuthCredentialsRepository authCredentialsRepository;

    @Transactional
    @Override
    public void save(AuthCredentials authCredentials) {
        authCredentialsRepository.save(authCredentials);
    }

    @Transactional
    @Override
    public void delete(Integer userId) {
        authCredentialsRepository.deleteUserCredentials(userId);
    }

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        AuthCredentials auth = authCredentialsRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Map role(s) if needed; using simple no-authority example:
        return new org.springframework.security.core.userdetails.User(
                auth.getUsername(),
                auth.getPassword(),
                Collections.emptyList()
        );

    }
}
