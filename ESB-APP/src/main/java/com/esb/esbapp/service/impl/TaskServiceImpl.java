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
    @Transactional
    public User completeTask(Long userId, Long taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        Optional<User> userOpt = userRepository.findById(userId);
        if (taskOpt.isEmpty() || userOpt.isEmpty()) {
            return null;
        }
        Task task = taskOpt.get();
        User user = userOpt.get();
        TaskRecord record = new TaskRecord();
        record.setUserId(userId);
        record.setTaskId(taskId);
        record.setCompletedDate(LocalDate.now());
        record.setEarnedPoints(task.getRewardPoints());
        taskRecordRepository.save(record);
        user.setDailyPoints(user.getDailyPoints() + task.getRewardPoints());
        user.setWeeklyPoints(user.getWeeklyPoints() + task.getRewardPoints());
        user.setTotalPoints(user.getTotalPoints() + task.getRewardPoints());
        userRepository.save(user);
        return user;
    }
}
