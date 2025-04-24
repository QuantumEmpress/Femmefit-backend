package FemmeFit.demo2.service;

import FemmeFit.demo2.dto.ExerciseDto;
import FemmeFit.demo2.dto.WorkoutDto;
import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.repository.UserRepository;
import FemmeFit.demo2.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    public final WorkoutRepository workoutRepository;
    public final UserService userService;
    public final UserRepository userRepository;


    public WorkoutService(WorkoutRepository workoutRepository, UserService userService, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        Workout workout = new Workout();
        workout.setWorkoutIntensity(workoutDto.getWorkoutIntensity());
        workout.setSubtitle(workoutDto.getSubtitle());
        workout.setTitle(workoutDto.getTitle());
        workout.setCalories(workoutDto.getCalories());
        workout.setDuration(workoutDto.getDuration());
        workout.setImagePath(workoutDto.getImagePath());
        if (workoutDto.getExercises() != null) {
            List<Exercise> exercises = workoutDto.getExercises().stream()
                    .map(exerciseDto -> {
                        Exercise exercise = new Exercise();
                        exercise.setReps(exerciseDto.getReps());
                        exercise.setSets(exerciseDto.getSets());
                        exercise.setRestInterval(exerciseDto.getRestInterval());
                        exercise.setName(exerciseDto.getName());
                        exercise.setWorkout(workout);
                        return exercise;
                    }).collect(Collectors.toList());
            workout.setExercises(exercises);
        }
        Workout savedWorkout = workoutRepository.save(workout);
        return mapToDto(savedWorkout);
    }

    public WorkoutDto mapToDto(Workout workout) {
        WorkoutDto workoutDto = new WorkoutDto();
        workoutDto.setId(workout.getId());
        workoutDto.setTitle(workout.getTitle());
        workoutDto.setSubtitle(workout.getSubtitle());
        workoutDto.setImagePath(workout.getImagePath());
        workoutDto.setCalories(workout.getCalories());
        workoutDto.setDuration(workout.getDuration());
        workoutDto.setWorkoutIntensity(workout.getWorkoutIntensity());
        if (workout.getExercises() != null) {
            List<ExerciseDto> exerciseDtos = workout.getExercises().stream()
                    .map(exercise -> {
                        ExerciseDto exerciseDto = new ExerciseDto();
                        exerciseDto.setId(exercise.getId());
                        exerciseDto.setName(exercise.getName());
                        exerciseDto.setReps(exercise.getReps());
                        exerciseDto.setSets(exercise.getSets());
                        exerciseDto.setRestInterval(exercise.getRestInterval());
                        return exerciseDto;
                    })
                    .collect(Collectors.toList());
            workoutDto.setExercises(exerciseDtos);
        }
        return workoutDto;
    }

    public User updateUserWorkout(String userEmail, List<Long> workoutIds) {
        User user = userService.getUserById(userEmail);
        List<Workout> workouts = workoutRepository.findAllById(workoutIds);
        user.getSavedWorkouts().addAll(workouts);
        return userRepository.save(user);

    }

    public List<WorkoutDto> getUnselectedWorkouts(String userEmail) {
        User user = userService.getUserById(userEmail);
        List<Long> userWorkoutIds = user.getSavedWorkouts().stream().map(Workout::getId).collect(Collectors.toList());
        if (userWorkoutIds.isEmpty()) {
            return workoutRepository.findAll().stream()
                    .map(workout -> new WorkoutDto(workout, userEmail))
                    .collect(Collectors.toList());
        }
        return workoutRepository.findByIdNotIn(userWorkoutIds).stream().map(workout -> new WorkoutDto(workout, userEmail)).collect(Collectors.toList());

    }

 
    public List<WorkoutDto> getUserWorkouts(String userId) {
        User user = userService.getUserById(userId);
        return user.getSavedWorkouts().stream()
                .map(workout -> new WorkoutDto(workout, userId))
                .collect(Collectors.toList());
    }
    public void removeWorkoutFromUser(String userEmail, Long workoutId) {
        // 1. Find the user
        User user = userService.getUserById(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + userEmail);
        }

        // 2. Check if the user has saved workouts
        if (user.getSavedWorkouts() == null || user.getSavedWorkouts().isEmpty()) {
            throw new RuntimeException("User has no saved workouts");
        }

        // 3. Remove the workout if it exists in the user's list
        boolean wasRemoved = user.getSavedWorkouts().removeIf(workout -> workout.getId().equals(workoutId));

        if (!wasRemoved) {
            throw new RuntimeException("Workout not found in user's saved workouts");
        }

        // 4. Save the updated user
        userRepository.save(user);
    }
}
