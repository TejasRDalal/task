package com.taskmanager.task.controller;

import com.taskmanager.task.pojo.Task;
import com.taskmanager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateTask(@RequestBody Task task) {
        taskService.addTask(task);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task updated successfully");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/get/{taskId}")
    public Task getTask(@PathVariable Integer taskId) {
        return taskService.getTaskById(taskId).isPresent() ? taskService.getTaskById(taskId).get() : null;
    }

}
