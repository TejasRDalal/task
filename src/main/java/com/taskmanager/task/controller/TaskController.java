package com.taskmanager.task.controller;

import com.taskmanager.task.pojo.Task;
import com.taskmanager.task.pojo.Users;
import com.taskmanager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public String addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return "Task added successfully";
    }

    @GetMapping("/getAll")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return "Task deleted successfully";
    }

    @GetMapping("/get/{taskId}")
    public Task getTask(@PathVariable Integer taskId) {
        return taskService.getTaskById(taskId).isPresent() ? taskService.getTaskById(taskId).get() : null;
    }

}
