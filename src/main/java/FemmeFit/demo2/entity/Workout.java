
package FemmeFit.demo2.entity;

import FemmeFit.demo2.enums.WorkoutIntensity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;
    @Enumerated(EnumType.STRING)
    private WorkoutIntensity workoutIntensity;
    private int duration;
    private int calories;
    private String imagePath;
    @ManyToMany(mappedBy = "savedWorkouts", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<User> usersWhoAdded;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Exercise> exercises;

    public Workout() {
    }

    public Workout(Long id, String title, String subtitle, int duration, int calories, List<User> usersWhoAdded, List<Exercise> exercises,WorkoutIntensity workoutIntensity, String imagePath) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.duration = duration;
        this.calories = calories;
        this.usersWhoAdded = usersWhoAdded;
        this.exercises = exercises;
        this.workoutIntensity=workoutIntensity;
        this.imagePath=imagePath;

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public List<User> getUser() {
        return usersWhoAdded;
    }

    public List<User> getUsersWhoAdded() {
        return usersWhoAdded;
    }

    public void setUsersWhoAdded(List<User> usersWhoAdded) {
        this.usersWhoAdded = usersWhoAdded;
    }

    public void setUser(List<User> user) {
        this.usersWhoAdded = usersWhoAdded;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public WorkoutIntensity getWorkoutIntensity() {
        return workoutIntensity;
    }

    public void setWorkoutIntensity(WorkoutIntensity workoutIntensity) {
        this.workoutIntensity = workoutIntensity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}