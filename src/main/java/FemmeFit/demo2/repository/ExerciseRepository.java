package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByUserId(Long userId);

    List<Exercise> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}