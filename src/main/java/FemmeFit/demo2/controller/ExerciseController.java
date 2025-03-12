package FemmeFit.demo2.controller;

import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise savedExercise = exerciseService.createExercise(exercise);
        return ResponseEntity.ok(savedExercise);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Exercise>> getExercisesByUserId(@PathVariable Long userId) {
        List<Exercise> exercises = exerciseService.getExercisesByUserId(userId);
        return ResponseEntity.ok(exercises);
    }
}