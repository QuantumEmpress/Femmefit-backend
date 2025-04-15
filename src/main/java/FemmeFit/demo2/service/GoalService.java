package FemmeFit.demo2.service;

import FemmeFit.demo2.dto.GoalProgressDTO;
import FemmeFit.demo2.entity.Goal;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.exception.ResourceNotFoundException;
import FemmeFit.demo2.repository.GoalRepository;
import FemmeFit.demo2.repository.UserRepository;
import FemmeFit.demo2.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    public Goal createGoal(Goal goal, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        goal.setUser(user);
        return goalRepository.save(goal);
    }

    public List<Goal> getGoalsByUserId(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

    public GoalProgressDTO getGoalProgress(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new ResourceNotFoundException("Goal not found with id: " + goalId));

        LocalDate startDate = goal.getCreatedAt().toLocalDate();
        LocalDate endDate = LocalDate.now();

        List<Workout> workouts = workoutRepository.findByUserIdAndDateBetween(
                goal.getUser().getId(), startDate, endDate);

        int totalCalories = calculateTotalCalories(workouts);
        int totalDuration = calculateTotalDuration(workouts);

        GoalProgressDTO dto = new GoalProgressDTO();
        dto.setGoalId(goalId);
        dto.setDescription(goal.getDescription());
        dto.setCurrentCalories(totalCalories);
        dto.setCurrentDuration(totalDuration);
        dto.setTargetCalories(goal.getTargetCalories());
        dto.setTargetDuration(goal.getTargetDuration());
        dto.setProgressPercentage(goal.getProgressPercentage(totalCalories, totalDuration));
        dto.setStatus(goal.getStatus());
        dto.setDaysRemaining(ChronoUnit.DAYS.between(LocalDate.now(), goal.getTargetDate()));

        return dto;
    }

    public List<GoalProgressDTO> getAllGoalsProgress(Long userId) {
        LocalDate today = LocalDate.now();
        return goalRepository.findByUserIdAndTargetDateGreaterThanEqual(userId, today.minusMonths(1))
                .stream()
                .map(goal -> getGoalProgress(goal.getId()))
                .collect(Collectors.toList());
    }

    private int calculateTotalCalories(List<Workout> workouts) {
        return workouts.stream()
                .mapToInt(Workout::getTotalCaloriesBurned)
                .sum();
    }

    private int calculateTotalDuration(List<Workout> workouts) {
        return workouts.stream()
                .mapToInt(Workout::getDuration)
                .sum();
    }
}