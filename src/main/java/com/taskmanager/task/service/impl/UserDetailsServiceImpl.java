package com.taskmanager.task.service.impl;

import com.taskmanager.task.pojo.AuthCredentials;
import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.repositories.AuthCredentialsRepository;
import com.taskmanager.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
        user.setPassword(authCredentials.get().getPassword());
        user.setUsername(authCredentials.get().getUsername());

        return com.taskmanager.task.service.impl.CustomUserDetails.build(user);
    }
}
