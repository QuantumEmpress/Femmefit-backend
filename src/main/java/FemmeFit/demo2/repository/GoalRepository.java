package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(Long userId);
    List<Goal> findByUserIdAndTargetDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    List<Goal> findByUserIdAndTargetDateAfter(Long userId, LocalDate date);

    // New method to find goals by user with target date in future
    List<Goal> findByUserIdAndTargetDateGreaterThanEqual(Long userId, LocalDate date);
}