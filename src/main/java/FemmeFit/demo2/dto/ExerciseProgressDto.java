package FemmeFit.demo2.dto;

import java.time.LocalDateTime;

public class ExerciseProgressDto {
    private Long id;
    private Long workoutProgressId;
    private Long exerciseId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int completedSets;
    private boolean completed;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getWorkoutProgressId() { return workoutProgressId; }
    public void setWorkoutProgressId(Long workoutProgressId) { this.workoutProgressId = workoutProgressId; }
    public Long getExerciseId() { return exerciseId; }
    public void setExerciseId(Long exerciseId) { this.exerciseId = exerciseId; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public int getCompletedSets() { return completedSets; }
    public void setCompletedSets(int completedSets) { this.completedSets = completedSets; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}