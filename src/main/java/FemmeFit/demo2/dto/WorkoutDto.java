package FemmeFit.demo2.dto;

import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.Workout;

import java.util.List;

public class WorkoutDto {
    private Long id;
    private String title;
    private String subtitle;
    private int duration;
    private int calories;
    private boolean isAddedByUser;
    private User user;
    private List<Exercise> exercises;

    public WorkoutDto() {
    }

    public WorkoutDto(Workout workout, Long userId) {
        this.id = workout.getId();
        this.title = workout.getTitle();
        this.subtitle = workout.getSubtitle();
        this.duration = workout.getDuration();
        this.calories = workout.getCalories();
        this.user = (User) workout.getUser();
        this.exercises = workout.getExercises();
        this.isAddedByUser = workout.getUsersWhoAdded().stream()
                .anyMatch(user -> user.getEmail().equals(userId));
    }

    public WorkoutDto(Workout workout) {
    }

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

    public boolean isAddedByUser() {
        return isAddedByUser;
    }

    public void setAddedByUser(boolean addedByUser) {
        isAddedByUser = addedByUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
