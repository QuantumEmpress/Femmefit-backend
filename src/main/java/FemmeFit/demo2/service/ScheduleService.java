package FemmeFit.demo2.service;

import FemmeFit.demo2.dto.ScheduleDto;
import FemmeFit.demo2.entity.Schedule;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.Workout;
import FemmeFit.demo2.repository.ScheduleRepository;
import FemmeFit.demo2.repository.UserRepository;
import FemmeFit.demo2.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           UserRepository userRepository,
                           WorkoutRepository workoutRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
    }

    @Transactional
    public ScheduleDto createSchedule(ScheduleDto scheduleDto) {
        User user = userRepository.findById(scheduleDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User with email " + scheduleDto.getUserId() + " not found"));

        Workout workout = workoutRepository.findById(scheduleDto.getWorkoutId())
                .orElseThrow(() -> new RuntimeException("Workout with ID " + scheduleDto.getWorkoutId() + " not found"));

        // Check for time slot conflict
        if (scheduleRepository.existsByUserAndDateAndTime(
                user, scheduleDto.getDate(), scheduleDto.getTime())) {
            throw new RuntimeException("Time slot already booked for this user");
        }

        Schedule schedule = new Schedule(
                user,
                workout,
                scheduleDto.getDate(),
                scheduleDto.getTime(),
                scheduleDto.getNotes()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return convertToDto(savedSchedule);
    }

    public List<ScheduleDto> getUserSchedules(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return scheduleRepository.findByUser(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ScheduleDto> getUserSchedulesByDate(String userId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return scheduleRepository.findByUserAndDate(user, date).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    private ScheduleDto convertToDto(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(schedule.getId());
        dto.setUserId(schedule.getUser().getEmail());
        dto.setWorkoutId(schedule.getWorkout().getId());
        dto.setDate(schedule.getDate());
        dto.setTime(schedule.getTime());
        dto.setNotes(schedule.getNotes());
        dto.setWorkoutTitle(schedule.getWorkout().getTitle());
        return dto;
    }
}