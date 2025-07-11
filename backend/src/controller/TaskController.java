package com.wattwise.controller;

import com.wattwise.dto.TaskDTO;
import com.wattwise.model.Task;
import com.wattwise.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<TaskDTO> getTasks() {
        return StreamSupport.stream(service.findAll().spliterator(), false)
                .map(t -> new TaskDTO(t.getId(), t.getName(), t.isCompleted()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO dto) {
        Task task = new Task(dto.getName(), dto.isCompleted());
        Task saved = service.save(task);
        return new TaskDTO(saved.getId(), saved.getName(), saved.isCompleted());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.delete(id);
    }
}
