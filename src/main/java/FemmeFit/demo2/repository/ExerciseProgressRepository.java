package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.entity.ExerciseProgress;
import FemmeFit.demo2.entity.WorkoutProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ExerciseProgressRepository extends JpaRepository<ExerciseProgress, Long> {
    List<ExerciseProgress> findByWorkoutProgress(WorkoutProgress workoutProgress);
    List<ExerciseProgress> findByWorkoutProgressAndExercise(WorkoutProgress workoutProgress, Exercise exercise);
}
