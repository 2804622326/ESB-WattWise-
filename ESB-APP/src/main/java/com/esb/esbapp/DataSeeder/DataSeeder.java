package com.esb.esbapp.DataSeeder;

import com.esb.esbapp.model.User;
import com.esb.esbapp.model.Task;
import com.esb.esbapp.model.RewardItem;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.repository.TaskRepository;
import com.esb.esbapp.repository.RewardItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final RewardItemRepository rewardItemRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            seedUsers();
        }
        if (taskRepository.count() == 0) {
            seedTasks();
        }
        if (rewardItemRepository.count() == 0) {
            seedRewards();
        }
    }

    private void seedUsers() {
        List<User> users = List.of(
                new User(null, "Viggo", "https://example.com/avatars/viggo1.png", 200, 0, 0, 3.5, 20.0, 60.0),
                new User(null, "Lumi", "https://example.com/avatars/lumi.png", 180, 0, 0, 2.8, 18.0, 55.0),
                new User(null, "Viggo", "https://example.com/avatars/viggo2.png", 120, 0, 0, 3.1, 19.0, 52.0),
                new User(null, "Boden", "https://example.com/avatars/boden.png", 100, 0, 0, 2.0, 16.0, 50.0),
                new User(null, "Jett", "https://example.com/avatars/jett.png", 95, 0, 0, 2.5, 15.0, 48.0),
                new User(null, "Zara", "https://example.com/avatars/zara.png", 90, 0, 0, 2.4, 15.5, 47.0),
                new User(null, "Gwen", "https://example.com/avatars/gwen.png", 80, 0, 0, 2.1, 14.0, 45.0),
                new User(null, "James", "https://example.com/avatars/james.png", 40, 0, 0, 2.2, 13.0, 40.0),
                new User(null, "Vivian", "https://example.com/avatars/vivian.png", 30, 0, 0, 1.9, 12.0, 38.0),
                new User(null, "Theo", "https://example.com/avatars/theo.png", 30, 0, 0, 1.8, 11.0, 36.0)
        );
        userRepository.saveAll(users);
        System.out.println("✅ Seeded 10 users.");
    }

    private void seedTasks() {
        List<Task> tasks = List.of(
                new Task(null, "Share Challenge", "Invite a neighbor or friend to join the challenge.", 10),
                new Task(null, "Delay Your Laundry", "Use your washing machine after 9pm today.", 20),
                new Task(null, "Unplug Idle Electronics", "Unplug 3+ unused devices or chargers.", 15),
                new Task(null, "Shorten Your Shower", "Keep today’s shower under 5 minutes.", 15),
                new Task(null, "Turn Off Lights", "Turn off lights in unused rooms for 3+ hours.", 10),
                new Task(null, "Cook with a Lid On", "Save energy by cooking with lids today.", 10)
        );
        taskRepository.saveAll(tasks);
        System.out.println("✅ Seeded 6 tasks.");
    }

    private void seedRewards() {
        List<RewardItem> rewards = List.of(
                new RewardItem(null, "Electricity Deduction Voucher €5", "€5 off your next electricity bill", 450, "https://example.com/images/electricity-5.png"),
                new RewardItem(null, "Smart Meter", "Track your energy usage", 1200, "https://example.com/images/smart-meter.png"),
                new RewardItem(null, "Supermarket Voucher €5", "Valid in major stores", 500, "https://example.com/images/supermarket.png"),
                new RewardItem(null, "Electricity Deduction Voucher €1", "€1 off electricity bill", 100, "https://example.com/images/electricity-1.png"),
                new RewardItem(null, "Mini Plant Planting Set", "Grow your own herbs or flowers", 400, "https://example.com/images/plant.png"),
                new RewardItem(null, "Community Sharing Tool Voucher", "Borrow tools from community center", 300, "https://example.com/images/tools.png")
        );
        rewardItemRepository.saveAll(rewards);
        System.out.println("✅ Seeded 6 reward items.");
    }
}