package com.esb.esbapp.controller;

import com.esb.esbapp.dto.CommunitySummaryDTO;
import com.esb.esbapp.dto.UserSummaryDTO;
import com.esb.esbapp.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/user-summary/{userId}")
    public UserSummaryDTO getUserSummary(@PathVariable Long userId) {
        return homeService.getUserSummary(userId);
    }

    @GetMapping("/community-summary")
    public CommunitySummaryDTO getCommunitySummary() {
        return homeService.getCommunitySummary();
    }
}
