package FemmeFit.demo2.controller;

import FemmeFit.demo2.dto.ExerciseDTO;
import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ExerciseDTO> createExercise(
            @RequestBody ExerciseDTO exerciseDTO,
            @PathVariable Long userId) {

        Exercise exercise = new Exercise();
        exercise.setName(exerciseDTO.getName());
        exercise.setDescription(exerciseDTO.getDescription());
        exercise.setCaloriesBurned(exerciseDTO.getCaloriesBurned());
        exercise.setDuration(exerciseDTO.getDuration());
        exercise.setDate(exerciseDTO.getDate() != null ? exerciseDTO.getDate() : LocalDate.now());

        Exercise createdExercise = exerciseService.createExercise(exercise, userId);
        return ResponseEntity.ok(mapToDTO(createdExercise));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByUserId(@PathVariable Long userId) {
        List<Exercise> exercises = exerciseService.getExercisesByUserId(userId);
        List<ExerciseDTO> exerciseDTOs = exercises.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(exerciseDTOs);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByUserIdAndDateRange(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<Exercise> exercises = exerciseService.findByUserIdAndDateBetween(userId, startDate, endDate);
        List<ExerciseDTO> exerciseDTOs = exercises.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(exerciseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(mapToDTO(exercise));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDTO> updateExercise(
            @PathVariable Long id,
            @RequestBody ExerciseDTO exerciseDTO) {

        Exercise exercise = new Exercise();
        exercise.setName(exerciseDTO.getName());
        exercise.setDescription(exerciseDTO.getDescription());
        exercise.setCaloriesBurned(exerciseDTO.getCaloriesBurned());
        exercise.setDuration(exerciseDTO.getDuration());
        exercise.setDate(exerciseDTO.getDate());

        Exercise updatedExercise = exerciseService.updateExercise(id, exercise);
        return ResponseEntity.ok(mapToDTO(updatedExercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    // Helper method to convert Entity to DTO
    private ExerciseDTO mapToDTO(Exercise exercise) {
        return new ExerciseDTO(
                exercise.getId(),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getCaloriesBurned(),
                exercise.getDuration(),
                exercise.getDate()
        );
    }
}