package FemmeFit.demo2.service;

import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProgressReportService {
    @Autowired
    private WorkoutRepository workoutRepository;

    public int getTotalWorkouts(Long userId) {
        return workoutRepository.findByUser_Id(userId).size();
    }

    public int getTotalCaloriesBurned(Long userId) {
        List<Workout> workouts = workoutRepository.findByUser_Id(userId);
        return workouts.stream().mapToInt(Workout::getCaloriesBurned).sum();
    }

    public String getWeeklySummary(Long userId) {
        LocalDate startDate = LocalDate.now().minusDays(7);
        List<Workout> workouts = workoutRepository.findByUser_IdAndDateAfter(userId, startDate);

        int totalWorkouts = workouts.size();
        int totalCaloriesBurned = workouts.stream().mapToInt(Workout::getCaloriesBurned).sum();

        return String.format("Weekly Summary: %d workouts, %d calories burned", totalWorkouts, totalCaloriesBurned);
    }
}