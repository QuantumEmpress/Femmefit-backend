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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
@Tag(name = "Workout Management", description = "Endpoints for managing workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create a new workout",
            description = "Creates a new workout with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout created successfully",
                    content = @Content(schema = @Schema(implementation = WorkoutDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public WorkoutDto createWorkout(
            @Parameter(description = "Workout details to create", required = true)
            @RequestBody WorkoutDto workoutDto) {
        return workoutService.createWorkout(workoutDto);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all workouts",
            description = "Retrieves a list of all available workouts"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = @Content(schema = @Schema(implementation = WorkoutDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<WorkoutDto> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @PostMapping("/{workoutId}/add-to-user/{userId}")
    @Operation(
            summary = "Add workout to user",
            description = "Associates a workout with a specific user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout added to user successfully",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User or workout not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public User addWorkoutToUser(
            @Parameter(description = "ID of the workout to add", required = true)
            @PathVariable Long workoutId,
            @Parameter(description = "ID of the user to add the workout to", required = true)
            @PathVariable String userId) {
        return workoutService.addWorkoutToUser(userId, workoutId);
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Get workouts for user",
            description = "Retrieves all workouts associated with a specific user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user workouts",
                    content = @Content(schema = @Schema(implementation = WorkoutDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<WorkoutDto> getUserWorkouts(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable String userId) {
        return workoutService.getUserWorkouts(userId);
    }
}