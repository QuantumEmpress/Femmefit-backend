package FemmeFit.demo2.dto;

import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.entity.Workout;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutProgressDto {
    private Long id;
    private String userId;
    private Long workoutId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean completed;
    private List<ExerciseProgressDto> exerciseProgresses;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Long getWorkoutId() { return workoutId; }
    public void setWorkoutId(Long workoutId) { this.workoutId = workoutId; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public List<ExerciseProgressDto> getExerciseProgresses() { return exerciseProgresses; }
    public void setExerciseProgresses(List<ExerciseProgressDto> exerciseProgresses) { this.exerciseProgresses = exerciseProgresses; }
}