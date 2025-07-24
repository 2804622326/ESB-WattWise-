package com.esb.esbapp.service;

import com.esb.esbapp.model.Task;
import java.util.List;
import com.esb.esbapp.model.User;

public interface TaskService {
    List<Task> getTasks(String category, Boolean enabled);

    /**
     * Mark a task as completed and update the user's points accordingly.
     *
     * @param userId id of the user
     * @param taskId id of the task
     * @return updated user or {@code null} if user/task not found
     */
    User completeTask(Long userId, Long taskId);
}
