package com.HarshMaurya.TaskFlow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.HarshMaurya.TaskFlow.entity.Task;
import com.HarshMaurya.TaskFlow.entity.TaskStatus;
import com.HarshMaurya.TaskFlow.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Uses the custom query method you already wrote in TaskRepository
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByTaskStatus(status);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setTaskStatus(updatedTask.getTaskStatus());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setProject(updatedTask.getProject());
        existingTask.setAssignee(updatedTask.getAssignee());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}