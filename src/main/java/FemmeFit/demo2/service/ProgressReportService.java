package FemmeFit.demo2.service;

import FemmeFit.demo2.dto.GoalProgressDTO;
import FemmeFit.demo2.dto.ProgressTrend;
import FemmeFit.demo2.entity.*;
import FemmeFit.demo2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProgressReportService {

    @Autowired private ProgressReportRepository progressReportRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private WorkoutRepository workoutRepository;
    @Autowired private ExerciseRepository exerciseRepository;
    @Autowired private GoalRepository goalRepository;

    // Core report operations
    public ProgressReport createProgressReport(ProgressReport progressReport, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        progressReport.setUser(user);
        return progressReportRepository.save(progressReport);
    }

    public List<ProgressReport> getProgressReportsByUserId(Long userId) {
        return progressReportRepository.findByUserId(userId);
    }

    // Report generation
    public ProgressReport generateProgressReport(Long userId, LocalDate startDate,
                                                 LocalDate endDate, String periodType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Workout> workouts = workoutRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
        List<Exercise> exercises = exerciseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);

        int totalCalories = calculateTotalCalories(workouts);
        int totalDuration = calculateTotalDuration(workouts);
        String mostFrequentExercise = findMostFrequentExercise(exercises);
        double goalProgress = calculateGoalProgress(userId, totalCalories, totalDuration);

        ProgressReport report = new ProgressReport();
        report.setTotalCaloriesBurned(totalCalories);
        report.setTotalDuration(totalDuration);
        report.setReportDate(LocalDate.now());
        report.setUser(user);
        report.setMostFrequentExercise(mostFrequentExercise);
        report.setNumberOfWorkouts(workouts.size());
        report.setGoalProgress(goalProgress);
        report.setPeriodType(periodType);

        return progressReportRepository.save(report);
    }

    public ProgressReport generateWeeklyReport(Long userId) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(7);
        return generateProgressReport(userId, startDate, endDate, "weekly");
    }

    public ProgressReport generateMonthlyReport(Long userId) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        return generateProgressReport(userId, startDate, endDate, "monthly");
    }

    // Analytics functions
    public ProgressTrend getProgressTrend(Long userId, int weeks) {
        List<String> labels = new ArrayList<>();
        List<Double> calories = new ArrayList<>();
        List<Double> duration = new ArrayList<>();

        for (int i = 0; i < weeks; i++) {
            LocalDate weekStart = LocalDate.now().minusWeeks(weeks - i);
            LocalDate weekEnd = weekStart.plusDays(6);

            List<Workout> weeklyWorkouts = workoutRepository
                    .findByUserIdAndDateBetween(userId, weekStart, weekEnd);

            labels.add(weekStart + " to " + weekEnd);
            calories.add((double) calculateTotalCalories(weeklyWorkouts));
            duration.add((double) calculateTotalDuration(weeklyWorkouts));
        }

        return new ProgressTrend(labels, calories, duration);
    }

    public Map<String, Object> calculateGoalsProgress(Long userId) {
        LocalDate now = LocalDate.now();
        List<Goal> activeGoals = goalRepository.findByUserIdAndTargetDateAfter(userId, now.minusMonths(1));
        List<Workout> recentWorkouts = workoutRepository
                .findByUserIdAndDateBetween(userId, now.minusMonths(1), now);

        int totalCalories = calculateTotalCalories(recentWorkouts);
        int totalDuration = calculateTotalDuration(recentWorkouts);

        List<GoalProgressDTO> goalsProgress = activeGoals.stream()
                .map(goal -> {
                    GoalProgressDTO dto = new GoalProgressDTO();
                    dto.setGoalId(goal.getId());
                    dto.setDescription(goal.getDescription());
                    dto.setProgressPercentage(
                            goal.getProgressPercentage(totalCalories, totalDuration));
                    dto.setStatus(goal.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());

        return Map.of(
                "goalsProgress", goalsProgress,
                "totalCalories", totalCalories,
                "totalDuration", totalDuration
        );
    }

    public Map<String, Object> getCurrentProgressSnapshot(Long userId) {
        LocalDate today = LocalDate.now();
        return Map.of(
                "weekly", generateWeeklyReport(userId),
                "monthly", generateMonthlyReport(userId),
                "goalsProgress", calculateGoalsProgress(userId)
        );
    }

    // Helper methods
    private int calculateTotalCalories(List<Workout> workouts) {
        return workouts.stream().mapToInt(Workout::getTotalCaloriesBurned).sum();
    }

    private int calculateTotalDuration(List<Workout> workouts) {
        return workouts.stream().mapToInt(Workout::getDuration).sum();
    }

    private String findMostFrequentExercise(List<Exercise> exercises) {
        return exercises.stream()
                .collect(Collectors.groupingBy(Exercise::getName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No exercises");
    }

    private double calculateGoalProgress(Long userId, int totalCalories, int totalDuration) {
        return goalRepository.findByUserIdAndTargetDateAfter(userId, LocalDate.now().minusMonths(1))
                .stream()
                .mapToDouble(g -> g.getProgressPercentage(totalCalories, totalDuration))
                .average()
                .orElse(0);
    }
}