package com.planeador.tereas.controller;


import com.planeador.tereas.model.Task;
import com.planeador.tereas.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // Permite peticiones desde tu frontend (Live Server / puerto 5500, etc.)
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // 1. GET /api/tasks -> Recuperar todas las tareas
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks); // Devuelve 200 OK
    }

    // 2. POST /api/tasks -> Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task newTask) {
        if (newTask.getCompleted() == null) {
            newTask.setCompleted(false);
        }
        Task savedTask = taskRepository.save(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask); // Devuelve 201 Created
    }

    // 3. PUT /api/tasks/{id} -> Actualizar una tarea (editar datos o marcarla como completada)
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found si no existe
        }

        Task existingTask = optionalTask.get();
        existingTask.setName(taskDetails.getName());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setAssignedTo(taskDetails.getAssignedTo());
        existingTask.setDueDate(taskDetails.getDueDate());
        existingTask.setStatus(taskDetails.getStatus());

        if (taskDetails.getCompleted() != null) {
            existingTask.setCompleted(taskDetails.getCompleted());
        }

        Task updatedTask = taskRepository.save(existingTask);
        return ResponseEntity.ok(updatedTask); // Devuelve 200 OK
    }

    // 4. DELETE /api/tasks/{id} -> Eliminar una tarea por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found si no existe
        }

        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}