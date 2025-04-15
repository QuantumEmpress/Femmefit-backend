package FemmeFit.demo2.service;

import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.repository.ExerciseRepository;
import FemmeFit.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserRepository userRepository;

    public Exercise createExercise(Exercise exercise, Long userId) {
        // Fetch the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the user and date for the exercise
        exercise.setUser(user);
        exercise.setDate(LocalDate.now()); // Set the current date

        // Save the exercise
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getExercisesByUserId(Long userId) {
        return exerciseRepository.findByUserId(userId);
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    public Exercise updateExercise(Long id, Exercise exerciseDetails) {
        Exercise exercise = getExerciseById(id);
        exercise.setName(exerciseDetails.getName());
        exercise.setDescription(exerciseDetails.getDescription());
        exercise.setCaloriesBurned(exerciseDetails.getCaloriesBurned());
        exercise.setDuration(exerciseDetails.getDuration());
        exercise.setDate(exerciseDetails.getDate());
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate) {
        return exerciseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }
}