package FemmeFit.demo2.controller;

import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<?> logWorkout(@Valid @RequestBody Workout workout, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }

        Workout savedWorkout = workoutService.logWorkout(workout);
        return ResponseEntity.ok(savedWorkout);
    }

    @PutMapping("/{workoutId}")
    public ResponseEntity<?> updateWorkout(@PathVariable Long workoutId, @Valid @RequestBody Workout updatedWorkout, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }

        Workout workout = workoutService.updateWorkout(workoutId, updatedWorkout);
        return ResponseEntity.ok(workout);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Workout>> getWorkoutsByUserId(@PathVariable Long userId) {
        List<Workout> workouts = workoutService.getWorkoutsByUserId(userId);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long workoutId) {
        Workout workout = workoutService.getWorkoutById(workoutId);
        return ResponseEntity.ok(workout);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long workoutId) {
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> workouts = workoutService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }

    private ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}