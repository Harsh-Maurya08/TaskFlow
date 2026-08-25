package com.HarshMaurya.TaskFlow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HarshMaurya.TaskFlow.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment , Long> {
    List<Comment> findByTaskId(Long taskId);
}
