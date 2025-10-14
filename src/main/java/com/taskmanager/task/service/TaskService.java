package com.taskmanager.task.service;

import com.taskmanager.task.pojo.Task;
import com.taskmanager.task.pojo.Users;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void addTask(Task task);
    void deleteTask(Integer userId);
    Optional<Task> getTaskById(Integer userId);
    List<Task> getAllTasks();
}
