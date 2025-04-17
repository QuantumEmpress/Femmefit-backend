
package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
   
}