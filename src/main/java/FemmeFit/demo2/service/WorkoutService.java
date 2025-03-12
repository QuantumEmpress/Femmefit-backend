package FemmeFit.demo2.service;

import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.repository.UserRepository;
import FemmeFit.demo2.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Log a new workout for a user.
     *
     * @param workout The workout to be logged.
     * @return The saved workout.
     */
    public Workout logWorkout(Workout workout) {
        // Ensure the user object is not null
        if (workout.getUser() == null || workout.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        // Fetch the user from the database
        User user = userRepository.findById(workout.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the user in the workout
        workout.setUser(user);

        // Save the workout
        return workoutRepository.save(workout);
    }

    /**
     * Fetch all workouts for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of workouts for the user.
     */
    public List<Workout> getWorkoutsByUserId(Long userId) {
        return workoutRepository.findByUser_Id(userId);
    }

    /**
     * Fetch a workout by its ID.
     *
     * @param workoutId The ID of the workout.
     * @return The workout.
     */
    public Workout getWorkoutById(Long workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
    }

    /**
     * Update an existing workout.
     *
     * @param workoutId The ID of the workout to update.
     * @param updatedWorkout The updated workout details.
     * @return The updated workout.
     */
    public Workout updateWorkout(Long workoutId, Workout updatedWorkout) {
        Workout existingWorkout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        // Update the fields
        existingWorkout.setExerciseName(updatedWorkout.getExerciseName());
        existingWorkout.setDuration(updatedWorkout.getDuration());
        existingWorkout.setCaloriesBurned(updatedWorkout.getCaloriesBurned());
        existingWorkout.setDate(updatedWorkout.getDate());

        // Save the updated workout
        return workoutRepository.save(existingWorkout);
    }

    /**
     * Delete a workout by its ID.
     *
     * @param workoutId The ID of the workout to delete.
     */
    public void deleteWorkout(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        workoutRepository.delete(workout);
    }
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }
}
