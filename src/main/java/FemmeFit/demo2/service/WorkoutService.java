package FemmeFit.demo2.service;

import FemmeFit.demo2.dto.WorkoutDto;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.repository.UserRepository;
import FemmeFit.demo2.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserService userService, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public WorkoutDto createWorkout(WorkoutDto workoutDto){
        Workout newWorkout=new Workout();
        newWorkout.setId(workoutDto.getId());
        newWorkout.setTitle(workoutDto.getTitle());
        newWorkout.setSubtitle(workoutDto.getSubtitle());
        newWorkout.setCalories(workoutDto.getCalories());

        if (workoutDto.getExercises() != null) {
            newWorkout.setExercises(workoutDto.getExercises());
            newWorkout.getExercises().forEach(exercise -> exercise.setWorkout(newWorkout));
        }

        Workout savedWorkout = workoutRepository.save(newWorkout);
        return new WorkoutDto(savedWorkout, null);
    }

    public List<WorkoutDto> getAllWorkouts(){
       return workoutRepository.findAll().stream()
                .map(WorkoutDto::new)
                .collect(Collectors.toList());
    }

    public User addWorkoutToUser(String userId, Long workoutId) {
        User user = userService.getUserById(userId);
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        user.getSavedWorkouts().add(workout);
        workout.getUsersWhoAdded().add(user);

        workoutRepository.save(workout);
        return userRepository.save(user);
    }

    public List<WorkoutDto> getUserWorkouts(String userId){
        User user = userService.getUserById(userId);
        return user.getSavedWorkouts().stream()
                .map(WorkoutDto::new)
                .collect(Collectors.toList());
    }

}
