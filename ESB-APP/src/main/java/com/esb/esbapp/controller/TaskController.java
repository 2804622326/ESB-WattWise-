package com.esb.esbapp.controller;

import com.esb.esbapp.model.Task;
import com.esb.esbapp.model.TaskRecord;
import com.esb.esbapp.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> getTasks(@RequestParam(required = false) String category,
                               @RequestParam(required = false) Boolean enabled) {
        return taskService.getTasks(category, enabled);
    }

    @GetMapping("/completed/{userId}/{date}")
    public List<TaskRecord> getCompleted(@PathVariable Long userId,
                                         @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return taskService.getCompletedTasks(userId, date);
    }

    @PostMapping("/complete")
    public void complete(@RequestParam Long userId,
                         @RequestParam Long taskId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        taskService.completeTask(userId, taskId, date);
    }
}
