package com.todoBackend.service;

import com.todoBackend.exception.DuplicateTaskException;
import com.todoBackend.exception.TaskNotFoundException;
import com.todoBackend.model.Task;
import com.todoBackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getRecentTasks() {
        return repository.findRecentIncompleteTasks(PageRequest.of(0, 5));
    }

    public Task createTask(Task task) {
        if (repository.existsByTitle(task.getTitle())) {
            throw new DuplicateTaskException("A task with the same title already exists.");
        }

        try {
            return repository.save(task);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Duplicate entry: Task title or description already exists.", ex);
        }
    }

    public void markAsDone(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        task.setCompleted(true);
        repository.save(task);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }
}
