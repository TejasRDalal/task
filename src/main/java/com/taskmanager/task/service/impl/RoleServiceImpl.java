package com.taskmanager.task.service.impl;

import com.taskmanager.task.pojo.Role;
import com.taskmanager.task.repositories.RoleRepository;
import com.taskmanager.task.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleRepository.deleteById(roleId);

    }

    @Override
    public List<Role> getAllRoles() {
       return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Integer roleId) {
        return roleRepository.findById(roleId);
    }
}
