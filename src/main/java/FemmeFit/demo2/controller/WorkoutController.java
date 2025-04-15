package FemmeFit.demo2.controller;

import FemmeFit.demo2.dto.*;
import FemmeFit.demo2.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<WorkoutResponseDTO> createWorkout(
            @Valid @RequestBody WorkoutRequestDTO workoutDTO,
            @PathVariable Long userId) {

        WorkoutResponseDTO response = workoutService.createWorkout(workoutDTO, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutResponseDTO>> getWorkoutsByUserId(
            @PathVariable Long userId) {

        List<WorkoutResponseDTO> workouts = workoutService.getWorkoutsByUserId(userId);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponseDTO> getWorkoutById(@PathVariable Long id) {
        WorkoutResponseDTO workout = workoutService.getWorkoutById(id);
        return ResponseEntity.ok(workout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
}