package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.UserWorkoutStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWorkoutStatsRepository extends JpaRepository<UserWorkoutStats, Long> {
    UserWorkoutStats findByUser(User user);
}