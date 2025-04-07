package com.todoBackend.controller;

import com.todoBackend.model.Task;
import com.todoBackend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = service.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<Void> markAsDone(@PathVariable Long id) {
        service.markAsDone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Task>> getRecentTasks() {
        return ResponseEntity.ok(service.getRecentTasks());
    }
}
