package com.taskmanager.task.controller;

import com.taskmanager.task.pojo.Role;
import com.taskmanager.task.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addRole(@RequestBody Role role) {
        roleService.addRole(role);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Role added successfully");
        return ResponseEntity.ok(response);

    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateRole(@RequestBody Role role) {
        System.out.println("Update Request: "+role.toString());
        roleService.addRole(role);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Role updated successfully");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/getAll")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<Map<String, String>> deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Role deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{roleId}")
    public Role getRole(@PathVariable Integer roleId) {
        return roleService.getRoleById(roleId).isPresent() ? roleService.getRoleById(roleId).get() : null;
    }


}
