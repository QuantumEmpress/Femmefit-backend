package FemmeFit.demo2.dto;

import java.time.temporal.ChronoUnit;

public class GoalProgressDTO {
    private Long goalId;
    private String description;
    private double progressPercentage;
    private String status;
    private int currentCalories;
    private int currentDuration;
    private int targetCalories;
    private int targetDuration;
    private long daysRemaining;

    // Getters and Setters
    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentCalories() {
        return currentCalories;
    }

    public void setCurrentCalories(int currentCalories) {
        this.currentCalories = currentCalories;
    }

    public int getCurrentDuration() {
        return currentDuration;
    }

    public void setCurrentDuration(int currentDuration) {
        this.currentDuration = currentDuration;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(int targetCalories) {
        this.targetCalories = targetCalories;
    }

    public int getTargetDuration() {
        return targetDuration;
    }

    public void setTargetDuration(int targetDuration) {
        this.targetDuration = targetDuration;
    }

    public long getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(long daysRemaining) {
        this.daysRemaining = daysRemaining;
    }
}