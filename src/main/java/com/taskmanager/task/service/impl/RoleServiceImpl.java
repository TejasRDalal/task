package com.taskmanager.task.service.impl;

import com.taskmanager.task.pojo.Role;
import com.taskmanager.task.repositories.RoleRepository;
import com.taskmanager.task.service.RoleService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void deleteRole(Integer roleId) {
        roleRepository.updateRole(roleId);

    }

    @Override
    public List<Role> getAllRoles() {
       return roleRepository.findByFlagTrue();
    }

    @Override
    public Optional<Role> getRoleById(Integer roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        roleRepository.save(role);
    }
}
