package com.taskmanager.task.service;

import com.taskmanager.task.pojo.AuthCredentials;
import com.taskmanager.task.pojo.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IAuthCredentials {

    void save(AuthCredentials authCredentials);

    void delete(Integer userId);

    User loadUserByUsername(String userName) throws UsernameNotFoundException;
}
