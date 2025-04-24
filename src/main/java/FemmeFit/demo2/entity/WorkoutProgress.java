package FemmeFit.demo2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workout_progress")
public class WorkoutProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean completed;

    @OneToMany(mappedBy = "workoutProgress", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseProgress> exerciseProgresses;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Workout getWorkout() { return workout; }
    public void setWorkout(Workout workout) { this.workout = workout; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public List<ExerciseProgress> getExerciseProgresses() { return exerciseProgresses; }
    public void setExerciseProgresses(List<ExerciseProgress> exerciseProgresses) { this.exerciseProgresses = exerciseProgresses; }
}