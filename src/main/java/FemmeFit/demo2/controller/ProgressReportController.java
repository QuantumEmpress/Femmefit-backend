package FemmeFit.demo2.controller;

import FemmeFit.demo2.service.ProgressReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
public class ProgressReportController {
    @Autowired
    private ProgressReportService progressReportService;

    @GetMapping("/total-workouts/{userId}")
    public ResponseEntity<Integer> getTotalWorkouts(@PathVariable Long userId) {
        int totalWorkouts = progressReportService.getTotalWorkouts(userId);
        return ResponseEntity.ok(totalWorkouts);
    }

    @GetMapping("/total-calories/{userId}")
    public ResponseEntity<Integer> getTotalCaloriesBurned(@PathVariable Long userId) {
        int totalCalories = progressReportService.getTotalCaloriesBurned(userId);
        return ResponseEntity.ok(totalCalories);
    }

    @GetMapping("/weekly-summary/{userId}")
    public ResponseEntity<String> getWeeklySummary(@PathVariable Long userId) {
        String summary = progressReportService.getWeeklySummary(userId);
        return ResponseEntity.ok(summary);
    }
}