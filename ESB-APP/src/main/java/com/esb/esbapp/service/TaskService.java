package com.esb.esbapp.service;

import com.esb.esbapp.model.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id); // 可用于用户完成任务时复用
}