package com.HarshMaurya.TaskFlow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HarshMaurya.TaskFlow.entity.Task;
import com.HarshMaurya.TaskFlow.entity.TaskStatus;
import com.HarshMaurya.TaskFlow.entity.User;

public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByProjectId (Long projectId);
    List<Task> findByTaskStatus (TaskStatus status);
    List<Task> findByAssigneeAndTaskStatus(User assignee, TaskStatus status);
}
