package FemmeFit.demo2.service;

import FemmeFit.demo2.dto.ExerciseProgressDto;
import FemmeFit.demo2.dto.WorkoutProgressDto;
import FemmeFit.demo2.entity.*;
import FemmeFit.demo2.repository.ExerciseProgressRepository;
import FemmeFit.demo2.repository.ExerciseRepository;
import FemmeFit.demo2.repository.WorkoutProgressRepository;
import FemmeFit.demo2.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutProgressService {
    private final WorkoutProgressRepository workoutProgressRepository;
    private final ExerciseProgressRepository exerciseProgressRepository;
    private final UserService userService;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutProgressService(
            WorkoutProgressRepository workoutProgressRepository,
            ExerciseProgressRepository exerciseProgressRepository,
            UserService userService,
            WorkoutRepository workoutRepository,
            ExerciseRepository exerciseRepository) {
        this.workoutProgressRepository = workoutProgressRepository;
        this.exerciseProgressRepository = exerciseProgressRepository;
        this.userService = userService;
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public WorkoutProgressDto startWorkout(String userId, Long workoutId) {
        User user = userService.getUserById(userId);
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        WorkoutProgress progress = new WorkoutProgress();
        progress.setUser(user);
        progress.setWorkout(workout);
        progress.setStartTime(LocalDateTime.now());
        progress.setCompleted(false);

        WorkoutProgress savedProgress = workoutProgressRepository.save(progress);

        // Create initial exercise progress records
        List<ExerciseProgress> exerciseProgresses = workout.getExercises().stream()
                .map(exercise -> {
                    ExerciseProgress ep = new ExerciseProgress();
                    ep.setWorkoutProgress(savedProgress);
                    ep.setExercise(exercise);
                    ep.setCompletedSets(0);
                    ep.setCompleted(false);
                    return ep;
                })
                .collect(Collectors.toList());

        exerciseProgressRepository.saveAll(exerciseProgresses);
        savedProgress.setExerciseProgresses(exerciseProgresses);

        return convertToDto(savedProgress);
    }

    public ExerciseProgressDto updateExerciseProgress(Long workoutProgressId, Long exerciseId, int completedSets) {
        WorkoutProgress workoutProgress = workoutProgressRepository.findById(workoutProgressId)
                .orElseThrow(() -> new RuntimeException("Workout progress not found"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        List<ExerciseProgress> exerciseProgresses = exerciseProgressRepository
                .findByWorkoutProgressAndExercise(workoutProgress, exercise);

        if (exerciseProgresses.isEmpty()) {
            throw new RuntimeException("Exercise progress not found");
        }

        ExerciseProgress exerciseProgress = exerciseProgresses.getFirst();

        exerciseProgress.setCompletedSets(completedSets);
        if (completedSets >= exercise.getSets()) {
            exerciseProgress.setCompleted(true);
            exerciseProgress.setEndTime(LocalDateTime.now());
        }

        ExerciseProgress savedProgress = exerciseProgressRepository.save(exerciseProgress);
        return convertToDto(savedProgress);
    }

    @Transactional
    public WorkoutProgressDto completeWorkout(Long workoutProgressId) {
        WorkoutProgress workoutProgress = workoutProgressRepository.findById(workoutProgressId)
                .orElseThrow(() -> new RuntimeException("Workout progress not found"));

        workoutProgress.setCompleted(true);
        workoutProgress.setEndTime(LocalDateTime.now());

        // Mark all exercises as completed if not already
        workoutProgress.getExerciseProgresses().forEach(ep -> {
            if (!ep.isCompleted()) {
                ep.setCompleted(true);
                ep.setCompletedSets(ep.getExercise().getSets());
                ep.setEndTime(LocalDateTime.now());
            }
        });

        WorkoutProgress savedProgress = workoutProgressRepository.save(workoutProgress);
        return convertToDto(savedProgress);
    }

    public List<WorkoutProgressDto> getUserWorkoutHistory(String userId) {
        User user = userService.getUserById(userId);
        return workoutProgressRepository.findByUser(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private WorkoutProgressDto convertToDto(WorkoutProgress progress) {
        WorkoutProgressDto dto = new WorkoutProgressDto();
        dto.setId(progress.getId());
        dto.setUserId(progress.getUser().getEmail());
        dto.setWorkoutId(progress.getWorkout().getId());
        dto.setStartTime(progress.getStartTime());
        dto.setEndTime(progress.getEndTime());
        dto.setCompleted(progress.isCompleted());

        if (progress.getExerciseProgresses() != null) {
            dto.setExerciseProgresses(progress.getExerciseProgresses().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private ExerciseProgressDto convertToDto(ExerciseProgress progress) {
        ExerciseProgressDto dto = new ExerciseProgressDto();
        dto.setId(progress.getId());
        dto.setWorkoutProgressId(progress.getWorkoutProgress().getId());
        dto.setExerciseId(progress.getExercise().getId());
        dto.setStartTime(progress.getStartTime());
        dto.setEndTime(progress.getEndTime());
        dto.setCompletedSets(progress.getCompletedSets());
        dto.setCompleted(progress.isCompleted());
        return dto;
    }

    public WorkoutProgressDto getIncompleteWorkoutProgress(String userId, Long workoutId) {
        User user = userService.getUserById(userId);
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        List<WorkoutProgress> incompleteProgress = workoutProgressRepository
                .findByUserAndWorkoutAndCompletedFalse(user, workout);

        if (incompleteProgress.isEmpty()) {
            return null;
        }

        return convertToDto(incompleteProgress.getFirst());
    }
}
