package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.Schedule;
import FemmeFit.demo2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUser(User user);
    List<Schedule> findByUserAndDate(User user, LocalDate date);
    boolean existsByUserAndDateAndTime(User user, LocalDate date, LocalTime time);
}