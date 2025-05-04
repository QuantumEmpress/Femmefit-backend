package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.entity.WorkoutProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutProgressRepository extends JpaRepository<WorkoutProgress, Long> {
    List<WorkoutProgress> findByUser(User user);
    List<WorkoutProgress> findByUserAndWorkout(User user, Workout workout);
    List<WorkoutProgress> findByUserAndWorkoutAndCompletedFalse(User user, Workout workout);
    List<WorkoutProgress> findByUserAndCompletedTrue(User user);
    long countByUserAndCompletedTrue(User user);
    List<WorkoutProgress> findByUserAndWorkoutAndCompletedTrueOrderByEndTimeDesc(User user, Workout workout);
}