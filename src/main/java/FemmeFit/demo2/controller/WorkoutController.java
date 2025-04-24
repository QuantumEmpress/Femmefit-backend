package FemmeFit.demo2.controller;

import FemmeFit.demo2.dto.WorkoutDto;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.service.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }
    @PostMapping("/create")
    public WorkoutDto createWorkout(@RequestBody WorkoutDto workoutDto){
        return workoutService.createWorkout(workoutDto);
    }
    @PostMapping("/user/{userEmail}/workouts")
    public User updateUserWorkout(@PathVariable String userEmail, @RequestBody List<Long> workoutId){
        return workoutService.updateUserWorkout(userEmail, workoutId);
    }
    @GetMapping("/unselected/{userEmail}")
    public List<WorkoutDto> getUnselectedWorkouts(@PathVariable String userEmail) {
        return workoutService.getUnselectedWorkouts(userEmail);
    }
    @GetMapping("/user/{userId}")
    public List<WorkoutDto> getUserWorkouts(
            @PathVariable String userId) {
        return workoutService.getUserWorkouts(userId);
    }
    @DeleteMapping("/user/{userEmail}/remove-workout/{workoutId}")
    public ResponseEntity<String> removeWorkoutFromUser(
            @PathVariable String userEmail,
            @PathVariable Long workoutId) {

        workoutService.removeWorkoutFromUser(userEmail, workoutId);
        return ResponseEntity.ok("Workout removed successfully");
    }

}