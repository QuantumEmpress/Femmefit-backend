package FemmeFit.demo2.service;

import FemmeFit.demo2.entity.Goal;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.repository.GoalRepository;
import FemmeFit.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    public Goal createGoal(Goal goal) {
        // Fetch the user from the database
        User user = userRepository.findById(goal.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the user in the goal
        goal.setUser(user);

        // Save the goal
        return goalRepository.save(goal);
    }

    public List<Goal> getGoalsByUserId(Long userId) {
        return goalRepository.findByUser_Id(userId);
    }
}