package com.HarshMaurya.TaskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HarshMaurya.TaskFlow.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
