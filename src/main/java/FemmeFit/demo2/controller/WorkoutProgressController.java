package FemmeFit.demo2.controller;

import FemmeFit.demo2.dto.ExerciseProgressDto;
import FemmeFit.demo2.dto.WorkoutProgressDto;
import FemmeFit.demo2.entity.WorkoutProgress;
import FemmeFit.demo2.service.WorkoutProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "*")
public class WorkoutProgressController {
    private final WorkoutProgressService workoutProgressService;

    public WorkoutProgressController(WorkoutProgressService workoutProgressService) {
        this.workoutProgressService = workoutProgressService;
    }

    @PostMapping("/start/{userId}/{workoutId}")
    public WorkoutProgressDto startWorkout(
            @PathVariable String userId,
            @PathVariable Long workoutId) {
        return workoutProgressService.startWorkout(userId, workoutId);
    }

    @PutMapping("/exercise/{workoutProgressId}/{exerciseId}")
    public ExerciseProgressDto updateExerciseProgress(
            @PathVariable Long workoutProgressId,
            @PathVariable Long exerciseId,
            @RequestParam int completedSets) {
        return workoutProgressService.updateExerciseProgress(workoutProgressId, exerciseId, completedSets);
    }

    @PutMapping("/complete/{workoutProgressId}")
    public WorkoutProgressDto completeWorkout(@PathVariable Long workoutProgressId) {
        return workoutProgressService.completeWorkout(workoutProgressId);
    }

    @GetMapping("/history/{userId}")
    public List<WorkoutProgressDto> getUserWorkoutHistory(@PathVariable String userId) {
        return workoutProgressService.getUserWorkoutHistory(userId);
    }

    @GetMapping("/incomplete/{userId}/{workoutId}")
    public WorkoutProgressDto getIncompleteWorkoutProgress(
            @PathVariable String userId,
            @PathVariable Long workoutId
    ) {
        return workoutProgressService.getIncompleteWorkoutProgress(userId, workoutId);
    }

}