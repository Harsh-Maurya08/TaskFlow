package com.HarshMaurya.TaskFlow.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @NotNull(message = "Due Date is required")
    @Column(nullable = false)
    private LocalDate dueDate = LocalDate.now();

    @NotNull(message = "Project is required")
    @ManyToOne
    @JoinColumn(name = "project_id" , nullable = false)
    private Project project;

    @NotNull(message = "Owner is required")
    @ManyToOne
    @JoinColumn(name = "assignee_id" , nullable = false)
    private User assignee;


    //Constructors
    public Task(){

    }

    public Task( @NotBlank(message = "Title is required") String title,
            @NotBlank(message = "Description is required") String description, TaskStatus taskStatus, Priority priority,
            @NotNull(message = "Due Date is required") LocalDate dueDate,
            @NotNull(message = "Project is required") Project project,
            @NotNull(message = "Owner is required") User assignee) {
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.priority = priority;
        this.dueDate = dueDate;
        this.project = project;
        this.assignee = assignee;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public TaskStatus getTaskStatus() {
        return taskStatus;
    }


    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }


    public Priority getPriority() {
        return priority;
    }


    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }


    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public Project getProject() {
        return project;
    }


    public void setProject(Project project) {
        this.project = project;
    }


    public User getAssignee() {
        return assignee;
    }


    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

}
