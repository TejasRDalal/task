package com.taskmanager.task.service.impl;

import com.taskmanager.task.pojo.AuthCredentials;
import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.repositories.AuthCredentialsRepository;
import com.taskmanager.task.repositories.UserRepository;
import com.taskmanager.task.service.UserDetailsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AuthCredentialsRepository authCredentialsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<AuthCredentials> authCredentials = Optional.ofNullable(authCredentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));

        Users user = userRepository.findByUserId(authCredentials.get().getUser().getUserId());

        return com.taskmanager.task.service.impl.UserDetailsService.build(user);
    }
}
