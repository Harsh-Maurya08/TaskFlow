package com.HarshMaurya.TaskFlow.entity;

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
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Field is required")
    @Column(nullable =  false)
    private String description;

    @NotNull(message = "Owner is required")
    @ManyToOne
    @JoinColumn(name = "owner_id" , nullable = false)
    private User owner;

    //Constructors
    public Project(){

    }

    //Parameterize Constructor
    public Project(String name , String description , User owner){
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    //Getter and Setters
    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }
}
