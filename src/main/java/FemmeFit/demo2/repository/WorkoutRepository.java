
package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long>{
   List<Workout> findByIdNotIn(List<Long> workoutIds);
}