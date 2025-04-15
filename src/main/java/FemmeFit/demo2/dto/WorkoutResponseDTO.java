// File: src/main/java/FemmeFit/demo2/dto/WorkoutResponseDTO.java
package FemmeFit.demo2.dto;

import java.time.LocalDate;
import java.util.List;

public class WorkoutResponseDTO {
    private Long id;
    private LocalDate date;
    private Integer duration;
    private Integer totalCaloriesBurned;
    private List<ExerciseDTO> exercises;

    // Constructors
    public WorkoutResponseDTO() {}

    public WorkoutResponseDTO(Long id, LocalDate date, Integer duration,
                              Integer totalCaloriesBurned, List<ExerciseDTO> exercises) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.totalCaloriesBurned = totalCaloriesBurned;
        this.exercises = exercises;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public void setTotalCaloriesBurned(Integer totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }
}