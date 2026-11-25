package com.planeador.tereas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la tarea es obligatorio")
    @Size(min = 9, message = "El nombre debe tener más de 8 caracteres")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 16, message = "La descripción debe tener más de 15 caracteres")
    @Column(nullable = false, length = 500)
    private String description;

    @NotBlank(message = "El campo asignado a es obligatorio")
    @Size(min = 9, message = "El campo asignado a debe tener más de 8 caracteres")
    @Column(name = "assigned_to", nullable = false)
    private String assignedTo;

    @NotBlank(message = "La fecha de entrega es obligatoria")
    @Column(name = "due_date", nullable = false)
    private String dueDate;

    @NotBlank(message = "La prioridad/estado es obligatoria")
    @Column(nullable = false)
    private String status;

    @NotNull
    @Column(nullable = false)
    private Boolean completed = false;

    // Constructor vacío obligatorio para JPA
    public Task() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}