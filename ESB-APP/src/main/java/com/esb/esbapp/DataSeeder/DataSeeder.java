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
        // Use placeholder avatar URLs so each user has a distinct image
        List<User> users = List.of(
                new User(null, "James (Myself)", 150, 430, 1020, 2.5, 18.3, 71.6,
                        "https://i.pravatar.cc/150?img=10"),
                new User(null, "Neighbor A", 180, 520, 1600, 3.5, 20.0, 60.0,
                        "https://i.pravatar.cc/150?img=1"),
                new User(null, "Neighbor B", 210, 400, 1050, 2.8, 18.0, 55.0,
                        "https://i.pravatar.cc/150?img=2"),
                new User(null, "Neighbor C", 130, 460, 1800, 3.1, 19.0, 52.0,
                        "https://i.pravatar.cc/150?img=3"),
                new User(null, "Neighbor D", 170, 610, 1300, 2.0, 16.0, 50.0,
                        "https://i.pravatar.cc/150?img=4"),
                new User(null, "Neighbor E", 200, 480, 1100, 2.5, 15.0, 48.0,
                        "https://i.pravatar.cc/150?img=5"),
                new User(null, "Neighbor F", 120, 390, 1500, 2.4, 15.5, 47.0,
                        "https://i.pravatar.cc/150?img=6"),
                new User(null, "Neighbor G", 160, 570, 1250, 2.1, 14.0, 45.0,
                        "https://i.pravatar.cc/150?img=7"),
                new User(null, "Neighbor H", 190, 430, 1000, 2.2, 13.0, 40.0,
                        "https://i.pravatar.cc/150?img=8"),
                new User(null, "Neighbor I", 140, 440, 1350, 1.9, 12.0, 38.0,
                        "https://i.pravatar.cc/150?img=9")
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
                new RewardItem(null, "Smart Plug", "Energy monitoring plug", 450,
                        "https://picsum.photos/seed/smartplug/200"),
                new RewardItem(null, "Shopping Bag", "Reusable shopping bag", 300,
                        "https://picsum.photos/seed/bag/200"),
                new RewardItem(null, "Electricity Voucher €1", "€1 off electricity bill", 100,
                        "https://picsum.photos/seed/voucher1/200"),
                new RewardItem(null, "Tool Sharing Voucher", "Borrow tools from community center", 600,
                        "https://picsum.photos/seed/tools/200"),
                new RewardItem(null, "Supermarket €5", "€5 voucher for supermarkets", 500,
                        "https://picsum.photos/seed/supermarket/200"),
                new RewardItem(null, "Flower", "Mini plant growing set", 800,
                        "https://picsum.photos/seed/flower/200")
        );
        rewardItemRepository.saveAll(rewards);
        System.out.println("✅ Seeded 6 reward items.");
    }
}