// File: src/main/java/FemmeFit/demo2/dto/WorkoutRequestDTO.java
package FemmeFit.demo2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

public class WorkoutRequestDTO {
    @NotNull private LocalDate date;
    @Positive private Integer duration;
    @Positive private Integer totalCaloriesBurned;
    private List<Long> exerciseIds;

    // Getters and setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public Integer getTotalCaloriesBurned() { return totalCaloriesBurned; }
    public void setTotalCaloriesBurned(Integer totalCaloriesBurned) { this.totalCaloriesBurned = totalCaloriesBurned; }
    public List<Long> getExerciseIds() { return exerciseIds; }
    public void setExerciseIds(List<Long> exerciseIds) { this.exerciseIds = exerciseIds; }
}