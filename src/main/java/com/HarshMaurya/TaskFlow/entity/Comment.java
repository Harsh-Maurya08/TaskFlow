package com.HarshMaurya.TaskFlow.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "content is required")
    @Column(nullable = false)
    private String content;

    @NotNull(message = "TimeStamp is required")
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @NotNull(message = "Task is required")
    @ManyToOne
    @JoinColumn(name = "task_id" , nullable = false)
    private Task task;

    @NotNull(message = "User is required")
    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    private User author;

    public Comment(){

    }

    public Comment(@NotBlank(message = "content is required") String content,
            @NotNull(message = "Task is required") Task task, @NotNull(message = "User is required") User author) {
        this.content = content;
        this.task = task;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    
}
