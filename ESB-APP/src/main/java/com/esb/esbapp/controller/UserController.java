package com.esb.esbapp.controller;

import com.esb.esbapp.dto.CommunitySummaryDTO;
import com.esb.esbapp.dto.UserSummaryDTO;
import com.esb.esbapp.model.User;
import com.esb.esbapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户信息（积分和能耗）
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/summary")
    public UserSummaryDTO getUserSummary(@PathVariable Long id) {
        return userService.getUserSummary(id);
    }

    /**
     * 完成任务，增加用户积分
     */
    @PostMapping("/{id}/complete-task/{taskId}")
    public User completeTask(@PathVariable Long id, @PathVariable Long taskId) {
        return userService.completeTask(id, taskId);
    }

    /**
     * 兑换奖品，扣除积分
     */
    @PostMapping("/{id}/redeem/{rewardItemId}")
    public String redeemReward(@PathVariable Long id, @PathVariable Long rewardItemId) {
        return userService.redeemReward(id, rewardItemId);
    }

    /**
     * 每日排行榜（包含当前用户）
     */
    @GetMapping("/{id}/leaderboard/daily")
    public List<User> getDailyLeaderboard(@PathVariable Long id) {
        return userService.getTop10ByDailyPoints(id);
    }

    /**
     * 每周排行榜（包含当前用户）
     */
    @GetMapping("/{id}/leaderboard/weekly")
    public List<User> getWeeklyLeaderboard(@PathVariable Long id) {
        return userService.getTop10ByWeeklyPoints(id);
    }

    /**
     * 总榜排行榜（包含当前用户）
     */
    @GetMapping("/{id}/leaderboard/all")
    public List<User> getTotalLeaderboard(@PathVariable Long id) {
        return userService.getTop10ByTotalPoints(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/community-summary")
    public CommunitySummaryDTO getCommunitySummary() {
        return userService.getCommunitySummary();
    }

}