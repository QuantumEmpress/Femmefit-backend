package FemmeFit.demo2.dto;

import java.time.LocalDate;

public class ExerciseDTO {
    private Long id;
    private String name;
    private String description;
    private Integer caloriesBurned;
    private Integer duration;
    private LocalDate date;

    // Constructors, getters, and setters
    public ExerciseDTO() {}

    public ExerciseDTO(Long id, String name, String description, Integer caloriesBurned,
                       Integer duration,    LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.caloriesBurned = caloriesBurned;
        this.duration = duration;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Integer caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
// Getters and setters for all fields
}