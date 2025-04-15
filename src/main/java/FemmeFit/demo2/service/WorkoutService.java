// File: src/main/java/FemmeFit/demo2/service/WorkoutService.java
package FemmeFit.demo2.service;

import FemmeFit.demo2.dto.*;
import FemmeFit.demo2.entity.*;
import FemmeFit.demo2.exception.InvalidDateException;
import FemmeFit.demo2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import FemmeFit.demo2.dto.ExerciseDTO;
import FemmeFit.demo2.dto.WorkoutRequestDTO;
import FemmeFit.demo2.dto.WorkoutResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository,
                          UserRepository userRepository,
                          ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public WorkoutResponseDTO createWorkout(WorkoutRequestDTO workoutDTO, Long userId) {
        // Validate workout date
        if (workoutDTO.getDate() == null) {
            throw new InvalidDateException("Workout date cannot be null");
        }
        if (workoutDTO.getDate().isAfter(LocalDate.now())) {
            throw new InvalidDateException("Workout date cannot be in the future");
        }

        // Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Create workout
        Workout workout = new Workout();
        workout.setDate(workoutDTO.getDate());
        workout.setDuration(workoutDTO.getDuration());
        workout.setTotalCaloriesBurned(workoutDTO.getTotalCaloriesBurned());
        workout.setUser(user);

        // Add exercises
        if (workoutDTO.getExerciseIds() != null && !workoutDTO.getExerciseIds().isEmpty()) {
            Set<Exercise> exercises = exerciseRepository.findAllById(workoutDTO.getExerciseIds())
                    .stream()
                    .peek(ex -> {
                        if (ex.getDate() == null) {
                            throw new InvalidDateException("Exercise date cannot be null for exercise ID: " + ex.getId());
                        }
                        if (ex.getDate().isAfter(LocalDate.now())) {
                            throw new InvalidDateException("Exercise date cannot be in future for exercise ID: " + ex.getId());
                        }
                    })
                    .collect(Collectors.toSet());
            workout.setExercises(exercises);
        }

        Workout savedWorkout = workoutRepository.save(workout);
        return mapToResponseDTO(savedWorkout);
    }

    public List<WorkoutResponseDTO> getWorkoutsByUserId(Long userId) {
        return workoutRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public WorkoutResponseDTO getWorkoutById(Long id) {
        return workoutRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new RuntimeException("Workout not found with id: " + id));
    }

    @Transactional
    public void deleteWorkout(Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new RuntimeException("Workout not found with id: " + id);
        }
        workoutRepository.deleteById(id);
    }

    private WorkoutResponseDTO mapToResponseDTO(Workout workout) {
        WorkoutResponseDTO dto = new WorkoutResponseDTO();
        dto.setId(workout.getId());
        dto.setDate(workout.getDate());
        dto.setDuration(workout.getDuration());
        dto.setTotalCaloriesBurned(workout.getTotalCaloriesBurned());

        if (workout.getExercises() != null) {
            dto.setExercises(workout.getExercises().stream()
                    .map(this::mapToExerciseDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private ExerciseDTO mapToExerciseDTO(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setDescription(exercise.getDescription());
        dto.setCaloriesBurned(exercise.getCaloriesBurned());
        dto.setDuration(exercise.getDuration());
        dto.setDate(exercise.getDate());
        return dto;
    }
}