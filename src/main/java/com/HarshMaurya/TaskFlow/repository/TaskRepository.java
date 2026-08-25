package com.HarshMaurya.TaskFlow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HarshMaurya.TaskFlow.entity.Task;
import com.HarshMaurya.TaskFlow.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByProjectId (Long projectId);
    List<Task> findByTaskStatus (TaskStatus status);
}
