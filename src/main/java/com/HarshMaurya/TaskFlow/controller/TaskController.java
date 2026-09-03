package com.HarshMaurya.TaskFlow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.HarshMaurya.TaskFlow.entity.Task;
import com.HarshMaurya.TaskFlow.entity.TaskStatus;
import com.HarshMaurya.TaskFlow.entity.User;
import com.HarshMaurya.TaskFlow.service.TaskService;
import com.HarshMaurya.TaskFlow.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Example: GET /api/tasks/project/3  -> all tasks belonging to project id 3
    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

    // Example: GET /api/tasks/status/IN_PROGRESS
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,@Valid @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/completed/user/{userId}")
public List<Task> getCompletedTasksByUser(@PathVariable Long userId) {
    User user = userService.getUserById(userId); // you'll need to inject UserService into TaskController for this
    return taskService.getCompletedTasksByUser(user);
}
}