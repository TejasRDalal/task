package com.taskmanager.task.controller;

import com.taskmanager.task.pojo.Role;
import com.taskmanager.task.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public String addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return "Role added successfully";
    }

    @GetMapping("/getAll")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/delete/{roleId}")
    public String deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return "Role deleted successfully";
    }

    @GetMapping("/get/{roleId}")
    public Role getRole(@PathVariable Integer roleId) {
        return roleService.getRoleById(roleId).isPresent() ? roleService.getRoleById(roleId).get() : null;
    }


}
