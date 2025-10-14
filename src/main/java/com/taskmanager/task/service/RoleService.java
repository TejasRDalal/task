package com.taskmanager.task.service;

import com.taskmanager.task.pojo.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void addRole(Role role);
    void deleteRole(Integer roleId);
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Integer roleId);
}
