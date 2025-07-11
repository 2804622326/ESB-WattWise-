package com.wattwise.service;

import com.wattwise.model.Task;
import com.wattwise.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Iterable<Task> findAll() {
        return repository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
