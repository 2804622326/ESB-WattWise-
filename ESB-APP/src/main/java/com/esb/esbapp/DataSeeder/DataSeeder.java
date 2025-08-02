package com.esb.esbapp.DataSeeder;

import com.esb.esbapp.model.User;
import com.esb.esbapp.model.Task;
import com.esb.esbapp.model.RewardItem;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.repository.TaskRepository;
import com.esb.esbapp.repository.RewardItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final RewardItemRepository rewardItemRepository;

    public DataSeeder(UserRepository userRepository,
                      TaskRepository taskRepository,
                      RewardItemRepository rewardItemRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.rewardItemRepository = rewardItemRepository;
    }

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
        // Adjusted users so that the top 3 have different ranks for daily, weekly, and total points
        List<User> users = List.of(
                // User 1: Highest daily, lowest weekly, mid total
                new User(null, "Viggo", 300, 500, 600, 3.5, 20.0, 60.0),
                // User 2: Mid daily, highest weekly, lowest total
                new User(null, "Lumi", 200, 600, 800, 2.8, 18.0, 55.0),
                // User 3: Lowest daily, mid weekly, highest total
                new User(null, "Viggo", 100, 250, 900, 3.1, 19.0, 52.0),
                // The rest stay similar, but ensure no accidental tie in top 3
                new User(null, "Boden", 80, 150, 300, 2.0, 16.0, 50.0),
                new User(null, "Jett", 75, 140, 450, 2.5, 15.0, 48.0),
                new User(null, "Zara", 70, 230, 420, 2.4, 15.5, 47.0),
                new User(null, "Gwen", 60, 120, 400, 2.1, 14.0, 45.0),
                new User(null, "James", 40, 260, 620, 2.2, 13.0, 40.0),
                new User(null, "Vivian", 30, 300, 250, 1.9, 12.0, 38.0),
                new User(null, "Theo", 30, 160, 680, 1.8, 11.0, 36.0)
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
                new RewardItem(null, "Electricity Deduction Voucher €5", "€5 off your next electricity bill", 450),
                new RewardItem(null, "Smart Meter", "Track your energy usage", 1200),
                new RewardItem(null, "Supermarket Voucher €5", "Valid in major stores", 500),
                new RewardItem(null, "Electricity Deduction Voucher €1", "€1 off electricity bill", 100),
                new RewardItem(null, "Mini Plant Planting Set", "Grow your own herbs or flowers", 400),
                new RewardItem(null, "Community Sharing Tool Voucher", "Borrow tools from community center", 300)
        );
        rewardItemRepository.saveAll(rewards);
        System.out.println("✅ Seeded 6 reward items.");
    }
}