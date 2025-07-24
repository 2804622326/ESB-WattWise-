package com.esb.esbapp.controller;

import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.RewardService;
import com.esb.esbapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for user related endpoints.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final TaskService taskService;
    private final RewardService rewardService;

    public UserController(UserRepository userRepository, TaskService taskService, RewardService rewardService) {
        this.userRepository = userRepository;
        this.taskService = taskService;
        this.rewardService = rewardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/complete-task/{taskId}")
    public ResponseEntity<User> completeTask(@PathVariable Long userId, @PathVariable Long taskId) {
        User updated = taskService.completeTask(userId, taskId);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{userId}/redeem/{rewardItemId}")
    public ResponseEntity<String> redeem(@PathVariable Long userId, @PathVariable Long rewardItemId) {
        boolean ok = rewardService.redeem(userId, rewardItemId);
        if (!ok) {
            return ResponseEntity.badRequest().body("Not enough points.");
        }
        return ResponseEntity.ok("Success");
    }
}
