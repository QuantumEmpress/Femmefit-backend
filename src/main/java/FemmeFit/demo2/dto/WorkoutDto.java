package FemmeFit.demo2.dto;

import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.enums.WorkoutIntensity;

import java.util.List;
import java.util.stream.Collectors;

public class WorkoutDto {
    private WorkoutIntensity workoutIntensity;
    private String imagePath;
    private Long id;
    private String title;
    private String subtitle;
    private int duration;
    private int calories;
    private boolean isAddedByUser;
    private List<ExerciseDto> exercises;

    public WorkoutDto() {
    }

    public WorkoutDto(Workout workout, String userId) {
        this.id = workout.getId();
        this.title = workout.getTitle();
        this.subtitle = workout.getSubtitle();
        this.duration = workout.getDuration();
        this.calories = workout.getCalories();
        this.imagePath=workout.getImagePath();
        this.workoutIntensity=workout.getWorkoutIntensity();
        this.isAddedByUser = workout.getUsersWhoAdded().stream()
                .anyMatch(user -> user.getEmail().equals(userId));
        if (workout.getExercises() !=null){
            this.exercises=workout.getExercises().stream().map(exercise -> new ExerciseDto(
                    exercise.getId(),
                    exercise.getName(),
                    exercise.getReps(),
                    exercise.getSets(),
                    exercise.getRestInterval()
            )).collect(Collectors.toList());
        }
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

    public List<ExerciseDto> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDto> exercises) {
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
