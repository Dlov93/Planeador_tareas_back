package com.planeador.tereas.repository;


import com.planeador.tereas.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Al extender de JpaRepository, ya tienes listos métodos como:
    // findAll(), findById(), save(), deleteById(), existsById()
}
