package FemmeFit.demo2.service;

import FemmeFit.demo2.entity.Exercise;
import FemmeFit.demo2.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getExercisesByUserId(Long userId) {
        return exerciseRepository.findByUser_Id(userId);
    }
}