package FemmeFit.demo2.controller;

import FemmeFit.demo2.dto.UserWorkoutStatsDto;
import FemmeFit.demo2.service.UserWorkoutStatsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-stats")
public class UserWorkoutStatsController {
    private final UserWorkoutStatsService statsService;

    public UserWorkoutStatsController(UserWorkoutStatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/{userId}")
    public UserWorkoutStatsDto getUserStats(@PathVariable String userId) {
        return statsService.getStats(userId);
    }
}