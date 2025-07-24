package com.esb.esbapp.service;

import com.esb.esbapp.model.Task;
import com.esb.esbapp.model.TaskRecord;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    List<Task> getTasks(String category, Boolean enabled);

    List<TaskRecord> getCompletedTasks(Long userId, LocalDate date);

    void completeTask(Long userId, Long taskId, LocalDate date);
}
