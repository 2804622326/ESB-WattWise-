package com.esb.esbapp.service.impl;

import com.esb.esbapp.model.Task;
import com.esb.esbapp.model.TaskRecord;
import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.TaskRecordRepository;
import com.esb.esbapp.repository.TaskRepository;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskRecordRepository taskRecordRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskRecordRepository taskRecordRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskRecordRepository = taskRecordRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> getTasks(String category, Boolean enabled) {
        if (category != null) {
            return taskRepository.findByCategory(category);
        }
        if (Boolean.TRUE.equals(enabled)) {
            return taskRepository.findByEnabledTrue();
        }
        return taskRepository.findAll();
    }

    @Override
    public List<TaskRecord> getCompletedTasks(Long userId, LocalDate date) {
        return taskRecordRepository.findByUserIdAndCompletedDate(userId, date);
    }

    @Override
    @Transactional
    public void completeTask(Long userId, Long taskId, LocalDate date) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        Optional<User> userOpt = userRepository.findById(userId);
        if (taskOpt.isEmpty() || userOpt.isEmpty()) {
            return;
        }
        Task task = taskOpt.get();
        User user = userOpt.get();
        TaskRecord record = TaskRecord.builder()
                .userId(userId)
                .taskId(taskId)
                .completedDate(date)
                .earnedPoints(task.getPoints())
                .build();
        taskRecordRepository.save(record);
        user.setTotalPoints(user.getTotalPoints() + task.getPoints());
        userRepository.save(user);
    }
}
