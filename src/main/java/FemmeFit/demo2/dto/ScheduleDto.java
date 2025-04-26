package FemmeFit.demo2.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDto {
    private Long id;
    private String userId;
    private Long workoutId;
    private LocalDate date;
    private LocalTime time;
    private String notes;
    private String workoutTitle;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Long getWorkoutId() { return workoutId; }
    public void setWorkoutId(Long workoutId) { this.workoutId = workoutId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getWorkoutTitle() { return workoutTitle; }
    public void setWorkoutTitle(String workoutTitle) { this.workoutTitle = workoutTitle; }
}