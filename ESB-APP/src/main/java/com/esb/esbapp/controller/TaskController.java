package com.esb.esbapp.controller;

import com.esb.esbapp.model.Task;
import com.esb.esbapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(required = false) String category,
                               @RequestParam(required = false) Boolean enabled) {
        return taskService.getTasks(category, enabled);
    }
}
