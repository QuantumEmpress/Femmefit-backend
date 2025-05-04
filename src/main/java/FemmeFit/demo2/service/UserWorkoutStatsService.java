package FemmeFit.demo2.service;

import FemmeFit.demo2.dto.UserWorkoutStatsDto;
import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.entity.UserWorkoutStats;
import FemmeFit.demo2.repository.UserWorkoutStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserWorkoutStatsService {
    private final UserWorkoutStatsRepository statsRepository;
    private final UserService userService;

    public UserWorkoutStatsService(UserWorkoutStatsRepository statsRepository, UserService userService) {
        this.statsRepository = statsRepository;
        this.userService = userService;
    }

    @Transactional
    public void updateStats(String userId, int minutes, int calories) {
        User user = userService.getUserById(userId);
        UserWorkoutStats stats = statsRepository.findByUser(user);

        if (stats == null) {
            stats = new UserWorkoutStats();
            stats.setUser(user);
            stats.setTotalMinutes(minutes);
            stats.setTotalCalories(calories);
        } else {
            stats.setTotalMinutes(stats.getTotalMinutes() + minutes);
            stats.setTotalCalories(stats.getTotalCalories() + calories);
        }

        stats.setLastUpdated(LocalDateTime.now());
        statsRepository.save(stats);
    }

    public UserWorkoutStatsDto getStats(String userId) {
        User user = userService.getUserById(userId);
        UserWorkoutStats stats = statsRepository.findByUser(user);

        if (stats == null) {
            return new UserWorkoutStatsDto();
        }

        UserWorkoutStatsDto dto = new UserWorkoutStatsDto();
        dto.setUserId(userId);
        dto.setTotalMinutes(stats.getTotalMinutes());
        dto.setTotalCalories(stats.getTotalCalories());
        return dto;
    }
}